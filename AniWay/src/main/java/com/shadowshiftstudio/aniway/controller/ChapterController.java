package com.shadowshiftstudio.aniway.controller;

import com.shadowshiftstudio.aniway.dto.chapter.CreateChapterRequest;
import com.shadowshiftstudio.aniway.service.chapter.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('TRANSLATOR')")
    public ResponseEntity uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("chapter_id") Long id) {
        try {
            return ResponseEntity.ok(chapterService.uploadChapterImage(file, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('TRANSLATOR')")
    public ResponseEntity createChapter(@RequestBody CreateChapterRequest request) {
        try {
            return ResponseEntity.ok(chapterService.createChapter(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/images")
    public ResponseEntity getChapterImages(@RequestParam Long id, @RequestParam String username) {
        try {
            return ResponseEntity.ok(chapterService.getChapterImages(id, username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/like")
    public ResponseEntity likeChapter(@RequestParam(name = "title_id") Long titleId,
                                      @RequestParam String username) {
        try {
            return ResponseEntity.ok(chapterService.likeChapter(titleId, username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
