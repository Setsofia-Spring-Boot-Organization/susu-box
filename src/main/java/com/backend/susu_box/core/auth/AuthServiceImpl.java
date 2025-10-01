package com.backend.susu_box.core.auth;

import com.backend.susu_box.core.dao.Response;
import com.backend.susu_box.core.security.JwtUtil;
import com.backend.susu_box.core.user.UserEntity;
import com.backend.susu_box.core.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public ResponseEntity<Response<?>> signUpUser(UserSignupDto signUpDto, boolean isAdmin) {

        UserEntity user = UserEntity
                .builder()
                .fullName(signUpDto.fullName())
                .email(signUpDto.email())
                .password(passwordEncoder.encode(signUpDto.password()))
                .momoNumber(signUpDto.momoNumber())
                .momoChannel(signUpDto.momoChannel())
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .role(isAdmin ? UserEntity.UserRole.BOX_ADMIN : UserEntity.UserRole.CONTRIBUTOR)
                .build();

        UserEntity newUser = userRepository.saveAndFlush(user);

        return ResponseEntity.ok(
                Response.builder()
                        .message("New user created!")
                        .data(newUser)
                        .build()
        );
    }


    @Override
    public ResponseEntity<Response<?>> signInUser(UserSignInDto userSignInDto) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userSignInDto.email(),
                            userSignInDto.password()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserEntity user = userRepository.findByEmail(userSignInDto.email())
                    .orElseThrow(() -> new  UsernameNotFoundException("User with email " + userSignInDto.email() + " not found"));

            String token = jwtUtil.generateToken(user.getEmail());

            return ResponseEntity.ok(
                    Response.builder()
                            .message("Login successful")
                            .data(token)
                            .build()
            );

        } catch (AuthenticationException authenticationException) {

            log.error(authenticationException.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Response.builder()
                            .message("Bad credentials")
                            .build()
            );
        }
    }
}
