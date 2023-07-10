package com.shadowshiftstudio.aniway.entity;

import com.shadowshiftstudio.aniway.enums.TitleStatus;
import com.shadowshiftstudio.aniway.enums.TitleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="titles")
public class TitleEntity {
    @Id
    @GeneratedValue
    private long id;

    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 100, message = "{validation.name.size.too_long}")
    private String name;

    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 100, message = "{validation.name.size.too_long}")
    @Column(name="original_name")
    private String originalName;

    private int year;
    private String description;

    @Enumerated(STRING)
    private TitleStatus status;

    @Enumerated(STRING)
    private TitleType type;

    private int views;

    @OneToMany(mappedBy = "chapters")
    private Set<ChapterEntity> chapters;

    @ManyToMany()
    @JoinTable(
            name = "titles_genres",
            joinColumns = @JoinColumn(name="title_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private Set<GenreEntity> genres;

    @OneToMany(mappedBy = "title_comments")
    private Set<TitleCommentsEntity> comments;
}
