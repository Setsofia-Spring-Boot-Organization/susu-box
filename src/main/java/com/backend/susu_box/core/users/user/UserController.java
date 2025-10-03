package com.backend.susu_box.core.users.user;

import com.backend.susu_box.core.dao.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PutMapping(path = "/update/{user-id}")
    public ResponseEntity<Response<?>> updateUser(
            @PathVariable("user-id") String user_id,
            @RequestBody UserUpdateDto updateDto
    ) {
        return userServiceImpl.updateUser(user_id, updateDto);
    }


    @GetMapping(path = "/{user-id}")
    public ResponseEntity<Response<UserDataDao>> getUserData(@PathVariable("user-id") String userId) {
        return userServiceImpl.getUserData(userId);
    }
}
