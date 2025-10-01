package com.backend.susu_box.core.auth;

public record UserSignupDto(
        String fullName,
        String email,
        String password,
        String momoNumber,
        String momoChannel
) { }
