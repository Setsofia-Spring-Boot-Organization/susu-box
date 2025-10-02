package com.backend.susu_box.core.user;

import com.backend.susu_box.core.dao.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    /// Todo: use user data update dto
    @PutMapping(path = "/update/{user-id}")
    public ResponseEntity<Response<?>> updateUser(@PathVariable("user-id") String user_id) {
        return userServiceImpl.updateUser(user_id);
    }
}
