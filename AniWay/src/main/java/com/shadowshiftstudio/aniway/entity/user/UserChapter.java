package com.shadowshiftstudio.aniway.entity.user;

import com.shadowshiftstudio.aniway.entity.chapter.ChapterEntity;
import com.shadowshiftstudio.aniway.entity.user.keys.UserChapterKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info_of_chapters")
public class UserChapter {
    @EmbeddedId
    private UserChapterKey id;

    @ManyToOne
    @MapsId("chapterId")
    @JoinColumn(name = "chapter_id", referencedColumnName = "id")
    private ChapterEntity chapter;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "is_liked")
    private boolean isLiked;

    @Column(name = "is_read")
    private boolean isRead;
}
