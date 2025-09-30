package com.backend.susu_box.core.auth;

import com.backend.susu_box.core.dao.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Override
    public ResponseEntity<Response<?>> signUpUser(UserSignupDto signUpDto) {
        return null;
    }
}
