package com.backend.susu_box.core.users.user;

import com.backend.susu_box.core.dao.Response;
import com.backend.susu_box.core.exception.SusuBoxException;
import com.backend.susu_box.core.exception.SusuBoxExceptionMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserUtil userUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseEntity<Response<?>> updateUser(String userId, UserUpdateDto updateDto) {

        UserEntity user = userUtil.getUserById(userId);

        try {

            user.setFirstName(updateDto.firstName().isEmpty() ? user.getFirstName() : updateDto.firstName());
            user.setLastName(updateDto.lastName().isEmpty() ? user.getLastName() : updateDto.lastName());

            user.setMomoNumber(updateDto.momoNumber().isEmpty() ? user.getMomoNumber() : updateDto.momoNumber());
            user.setMomoChannel(updateDto.momoChannel().isEmpty() ? user.getMomoChannel() : updateDto.momoChannel());

            user.setDateUpdated(LocalDateTime.now());

            userRepository.save(user);

            return ResponseEntity.ok(Response
                    .builder()
                    .message("Update successful")
                    .build()
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SusuBoxException(SusuBoxExceptionMessages.UPDATE_UNSUCCESSFUL);
        }
    }


    @Override
    public ResponseEntity<Response<UserDataDao>> getUserData(String userId) {
        return ResponseEntity.ok(
                Response.<UserDataDao>builder()
                        .message("User data")
                        .data(userUtil.buildUserDataResponse(userUtil.getUserById(userId)))
                        .build()
        );
    }
}
