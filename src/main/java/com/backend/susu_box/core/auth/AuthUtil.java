package com.backend.susu_box.core.auth;

import com.backend.susu_box.core.users.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtil {

    private final UserRepository userRepository;

    public boolean isExistingEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean isValidFields(UserSignupDto signupDto) {
        return !signupDto.firstName().trim().isEmpty() &&
                !signupDto.lastName().trim().isEmpty() &&
                !signupDto.email().trim().isEmpty() &&
                !signupDto.password().trim().isEmpty() &&
                isValidMoMoNumber(signupDto.momoNumber()) &&
                isValidMoMoChannel(signupDto.momoChannel());
    }

    private boolean isValidMoMoNumber(String number) {
        return number.trim().length() > 9;
    }

    private boolean isValidMoMoChannel(String channel) {
        switch (channel.trim()) {
            case "mtn_gh", "vodafone_gh", "airtel_gh", "tigo_gh" -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
