package com.backend.susu_box.core.users.user;

import com.backend.susu_box.core.dao.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Response<?>> updateUser(String userId, UserUpdateDto updateDto);

    ResponseEntity<Response<UserDataDao>> getUserData(String userId);
}
