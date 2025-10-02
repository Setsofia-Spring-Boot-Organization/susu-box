package com.backend.susu_box.core.user;

import com.backend.susu_box.core.dao.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Response<?>> updateUser(String userId);
}
