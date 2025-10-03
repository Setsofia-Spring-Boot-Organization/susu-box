package com.backend.susu_box.core.users.user;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDataDao(
        String firstName,
        String lastName,
        String email,
        String momoNumber,
        String momoChannel,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String role
) {
}
