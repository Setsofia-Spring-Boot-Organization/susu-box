package com.backend.susu_box.core.users.user;

public record UserUpdateDto(
        String firstName,
        String lastName,
        String momoNumber,
        String momoChannel
) { }
