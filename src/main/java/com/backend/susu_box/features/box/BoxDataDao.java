package com.backend.susu_box.features.box;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BoxDataDao(
        String id,
        String name,
        String description,
        Long contributionAmount,
        String frequency,
        String createdBy,
        String boxRules,
        List<BoxMember> boxMembers,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
