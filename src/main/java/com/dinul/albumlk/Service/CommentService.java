package com.dinul.albumlk.Service;
import com.dinul.albumlk.Entity.Comment;
import com.dinul.albumlk.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    // Constructor-based Dependency Injection
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Method to create a new comment
    public Comment createComment(Comment comment) {
        comment.setCreatedAt(comment.getCreatedAt()); // Ensure createdAt is set to current time
        return commentRepository.save(comment);  // Save and return the comment
    }

    // Method to get all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();  // Fetch all comments from the database
    }

    // Method to get a comment by its ID
    public Comment getCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id); // Find comment by ID
        return comment.orElse(null); // Return the comment if found, or null if not found
    }

    // Method to update an existing comment
    public Comment updateComment(Integer id, Comment updatedComment) {
        Optional<Comment> existingComment = commentRepository.findById(id);  // Check if the comment exists
        if (existingComment.isPresent()) {
            Comment comment = existingComment.get();
            comment.setContent(updatedComment.getContent()); // Update content field
            return commentRepository.save(comment); // Save and return the updated comment
        } else {
            return null; // Return null if comment is not found
        }
    }

    // Method to delete a comment by its ID
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id); // Delete the comment by its ID
    }
}