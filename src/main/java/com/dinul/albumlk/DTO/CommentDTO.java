package com.dinul.albumlk.DTO;

import java.time.LocalDateTime;

public class CommentDTO {

    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private Long albumId;        // Only the ID reference of the Album
    private Long reviewerId;     // Only the ID reference of the Reviewer
    private String reviewerName; // Optionally, include the reviewer's name for display

    // Constructors
    public CommentDTO() {
    }

    public CommentDTO(Integer id, String content, LocalDateTime createdAt, Long albumId, Long reviewerId, String reviewerName) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.albumId = albumId;
        this.reviewerId = reviewerId;
        this.reviewerName = reviewerName;
    }

    public CommentDTO(Integer id, String content, Long id1) {
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
}