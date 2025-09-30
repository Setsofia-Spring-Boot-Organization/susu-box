package com.backend.susu_box.core.auth;

import com.backend.susu_box.core.dao.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<Response<?>> signUpUser(
            @RequestBody UserSignupDto signUpDto
    ) {
        return authService.signUpUser(signUpDto);
    }
}
