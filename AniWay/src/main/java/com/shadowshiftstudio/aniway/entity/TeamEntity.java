package com.shadowshiftstudio.aniway.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="teams")
public class TeamEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="owner_id")
    private long ownerId;

    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 20, message = "{validation.name.size.too_long}")
    private String name;

    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 150, message = "{validation.name.size.too_long}")
    private String description;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "chapters")
    private Set<ChapterEntity> chapters;

    @ManyToMany()
    @JoinTable(
            name = "team_users",
            joinColumns = @JoinColumn(name="team_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private Set<UserEntity> users;
}
