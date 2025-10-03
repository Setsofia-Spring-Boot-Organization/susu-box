package com.backend.susu_box.core.auth;

import com.backend.susu_box.core.dao.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<Response<?>> signUpUser(
            @RequestBody UserSignupDto signUpDto,
            @RequestParam(required = false) boolean is_admin
    ) {
        return authService.signUpUser(signUpDto, is_admin);
    }


    @PostMapping(path = "/sign-in")
    public ResponseEntity<Response<?>> getUsers(
            @RequestBody UserSignInDto userSignInDto
    ) {
        return authService.signInUser(userSignInDto);
    }
}
