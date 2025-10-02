package com.backend.susu_box.core.auth;

public record UserSignupDto(
        String firstName,
        String lastName,
        String email,
        String password,
        String momoNumber,
        String momoChannel
) { }
