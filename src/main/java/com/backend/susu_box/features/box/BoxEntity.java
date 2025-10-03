package com.backend.susu_box.features.box;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "box")
public class BoxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String description;
    private Long contributionAmount;
    private ContributionFrequency frequency;

    private String createdBy;
    private String boxRules;

    @ElementCollection
    private List<BoxMember> boxMembers;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum ContributionFrequency {
        DAILY,
        WEEKLY,
        MONTHLY,
        QUARTERLY,
        YEARLY
    }
}
