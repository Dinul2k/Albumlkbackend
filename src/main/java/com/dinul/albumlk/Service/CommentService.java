package com.dinul.albumlk.Service;

import com.dinul.albumlk.DTO.CommentDTO;
import com.dinul.albumlk.Entity.Album;
import com.dinul.albumlk.Entity.Comment;
import com.dinul.albumlk.Entity.Reviewer; // Ensure Reviewer entity is imported
import com.dinul.albumlk.Repository.AlbumRepository;
import com.dinul.albumlk.Repository.CommentRepository;
import com.dinul.albumlk.Repository.ReviewerRepository; // If you have a repository for Reviewer
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AlbumRepository albumRepository;
    private final ReviewerRepository reviewerRepository; // If reviewer details are needed

    @Autowired
    public CommentService(CommentRepository commentRepository, AlbumRepository albumRepository, ReviewerRepository reviewerRepository) {
        this.commentRepository = commentRepository;
        this.albumRepository = albumRepository;
        this.reviewerRepository = reviewerRepository;
    }

    // Method to create a new comment with an associated album
    public Comment createComment(Comment comment, Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        comment.setAlbum(album);
        return commentRepository.save(comment);
    }

    // Method to create a new comment using CommentDTO
    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        // Associate with Album if ID is provided
        if (commentDTO.getAlbumId() != null) {
            Album album = albumRepository.findById(commentDTO.getAlbumId())
                    .orElseThrow(() -> new IllegalArgumentException("Album not found"));
            comment.setAlbum(album);
        }

        // Associate with Reviewer if ID is provided
        if (commentDTO.getReviewerId() != null) {
            Reviewer reviewer = reviewerRepository.findById(Math.toIntExact(commentDTO.getReviewerId()))
                    .orElseThrow(() -> new IllegalArgumentException("Reviewer not found"));
            comment.setReviewer(reviewer);
        }

        Comment savedComment = commentRepository.save(comment);
        return mapToDTO(savedComment);
    }

    // Method to get all comments
    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Method to get a comment by its ID
    public CommentDTO getCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.map(this::mapToDTO).orElse(null);
    }

    // Method to update an existing comment
    public CommentDTO updateComment(Integer id, CommentDTO updatedCommentDTO) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            Comment comment = existingComment.get();
            comment.setContent(updatedCommentDTO.getContent());

            // Update Album association if necessary
            if (updatedCommentDTO.getAlbumId() != null) {
                Album album = albumRepository.findById(updatedCommentDTO.getAlbumId())
                        .orElseThrow(() -> new IllegalArgumentException("Album not found"));
                comment.setAlbum(album);
            }

            // Update Reviewer association if necessary
            if (updatedCommentDTO.getReviewerId() != null) {
                Reviewer reviewer = reviewerRepository.findById(Math.toIntExact(updatedCommentDTO.getReviewerId()))
                        .orElseThrow(() -> new IllegalArgumentException("Reviewer not found"));
                comment.setReviewer(reviewer);
            }

            Comment updatedComment = commentRepository.save(comment);
            return mapToDTO(updatedComment);
        } else {
            return null;
        }
    }

    // Method to delete a comment by its ID
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    // Helper method to map Comment entity to CommentDTO
    private CommentDTO mapToDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getAlbum() != null ? comment.getAlbum().getId() : null,
                comment.getReviewer() != null ? comment.getReviewer().getId() : null,
                comment.getReviewer() != null ? comment.getReviewer().getUsername() : null
        );
    }
}
