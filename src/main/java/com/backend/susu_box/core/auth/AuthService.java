package com.backend.susu_box.core.auth;

import com.backend.susu_box.core.dao.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Response<?>> signUpUser(UserSignupDto signUpDto, boolean isAdmin);

    ResponseEntity<Response<?>> signInUser(UserSignInDto userSignInDto);
}
