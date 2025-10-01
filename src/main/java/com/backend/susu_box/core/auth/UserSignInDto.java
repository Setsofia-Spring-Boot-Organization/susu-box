package com.backend.susu_box.core.auth;

public record UserSignInDto(
        String email,
        String password
) { }
