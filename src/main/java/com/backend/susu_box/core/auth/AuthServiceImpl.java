package com.backend.susu_box.core.auth;

import com.backend.susu_box.core.dao.Response;
import com.backend.susu_box.core.exception.SusuBoxException;
import com.backend.susu_box.core.exception.SusuBoxExceptionMessages;
import com.backend.susu_box.core.security.JwtUtil;
import com.backend.susu_box.core.users.user.UserEntity;
import com.backend.susu_box.core.users.user.UserRepository;
import com.backend.susu_box.core.users.user.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthUtil authUtil;
    private final UserUtil userUtil;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResponseEntity<Response<?>> signUpUser(UserSignupDto signUpDto, boolean isAdmin) {

        if (authUtil.isExistingEmail(signUpDto.email())) throw new SusuBoxException(SusuBoxExceptionMessages.EMAIL_ALREADY_EXISTS);

        if (!authUtil.isValidFields(signUpDto)) throw new SusuBoxException(SusuBoxExceptionMessages.NO_EMPTY_FIELD_ALLOWED);

        try {
            UserEntity user = UserEntity
                    .builder()
                    .firstName(signUpDto.firstName())
                    .lastName(signUpDto.lastName())
                    .email(signUpDto.email().toLowerCase())
                    .password(passwordEncoder.encode(signUpDto.password()))
                    .momoNumber(signUpDto.momoNumber())
                    .momoChannel(signUpDto.momoChannel())
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .role(isAdmin ? UserEntity.UserRole.BOX_ADMIN : UserEntity.UserRole.CONTRIBUTOR)
                    .build();

            UserEntity newUser = userRepository.saveAndFlush(user);

            String token = jwtUtil.generateToken(newUser);

            return ResponseEntity.ok(
                    Response.builder()
                            .message("New user created!")
                            .data(Map.of("token", token))
                            .build()
            );
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new SusuBoxException(SusuBoxExceptionMessages.ERROR_CREATING_USER);
        }
    }


    @Override
    public ResponseEntity<Response<?>> signInUser(UserSignInDto userSignInDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userSignInDto.email().toLowerCase(),
                            userSignInDto.password()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserEntity user = userUtil.getUserByEmail(userSignInDto.email());

            String token = jwtUtil.generateToken(user);

            return ResponseEntity.ok(
                    Response.builder()
                            .message("Login successful")
                            .data(Map.of("token", token))
                            .build()
            );

        } catch (AuthenticationException authenticationException) {
            log.error(authenticationException.getMessage());
            throw new SusuBoxException(SusuBoxExceptionMessages.BAD_CREDENTIALS);
        }
    }
}
