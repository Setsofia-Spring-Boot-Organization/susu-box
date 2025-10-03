package com.backend.susu_box.features.box;

import com.backend.susu_box.core.users.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class BoxMember {
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "id")
    private UserEntity user;
    private LocalDateTime joinedAt;
}
