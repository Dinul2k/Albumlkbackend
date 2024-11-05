package com.dinul.albumlk.Controller;

import com.dinul.albumlk.DTO.CommentDTO;
import com.dinul.albumlk.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Create a new comment
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO savedCommentDTO = commentService.createComment(commentDTO);
        return new ResponseEntity<>(savedCommentDTO, HttpStatus.CREATED); // Return 201 Created status
    }

    // Get all comments
    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK); // Return 200 OK status
    }

    // Get a comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Integer id) {
        CommentDTO commentDTO = commentService.getCommentById(id);
        if (commentDTO != null) {
            return new ResponseEntity<>(commentDTO, HttpStatus.OK); // Return 200 OK status
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found status
        }
    }

    // Update an existing comment by ID
    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer id, @RequestBody CommentDTO updatedCommentDTO) {
        CommentDTO updatedComment = commentService.updateComment(id, updatedCommentDTO);
        if (updatedComment != null) {
            return new ResponseEntity<>(updatedComment, HttpStatus.OK); // Return 200 OK status
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found status
        }
    }

    // Delete a comment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        CommentDTO commentDTO = commentService.getCommentById(id);
        if (commentDTO != null) {
            commentService.deleteComment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
