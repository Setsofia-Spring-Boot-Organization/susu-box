package com.backend.susu_box.core.users.user;

public record NewBoxDto(
        String name,
        String description,
        Long contributionAmount,
        String frequency,
        String createdBy,
        String boxRules
) { }
