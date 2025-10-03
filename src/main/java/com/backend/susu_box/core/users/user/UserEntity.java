package com.backend.susu_box.core.users.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String momoNumber;
    private String momoChannel;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public enum UserRole {
        CONTRIBUTOR,
        BOX_ADMIN
    }
}
