package com.backend.susu_box.core.users.user;

import com.backend.susu_box.core.exception.SusuBoxException;
import com.backend.susu_box.core.exception.SusuBoxExceptionMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public UserEntity getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new SusuBoxException(SusuBoxExceptionMessages.USER_NOT_FOUND));
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new SusuBoxException(SusuBoxExceptionMessages.USER_NOT_FOUND));
    }

    public UserDataDao buildUserDataResponse(UserEntity user) {
        return UserDataDao
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .momoNumber(user.getMomoNumber())
                .momoChannel(user.getMomoChannel())
                .dateCreated(user.getDateCreated())
                .dateUpdated(user.getDateUpdated())
                .role(user.getRole().name())
                .build();
    }


    public boolean userIsAdmin(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new SusuBoxException(SusuBoxExceptionMessages.USER_NOT_FOUND));
        return user.getRole().equals(UserEntity.UserRole.BOX_ADMIN);
    }
}
