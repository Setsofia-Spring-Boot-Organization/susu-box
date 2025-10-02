package com.backend.susu_box.core.user;

import com.backend.susu_box.core.dao.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<Response<?>> updateUser(String userId) {
        return null;
    }
}
