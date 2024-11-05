package com.dinul.albumlk.Controller;

import com.dinul.albumlk.DTO.ReviewerDTO;
import com.dinul.albumlk.Service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviewers")
public class ReviewerController {

    private final ReviewerService reviewerService;

    @Autowired
    public ReviewerController(ReviewerService reviewerService) {
        this.reviewerService = reviewerService;
    }

    // Create a new reviewer
    @PostMapping
    public ResponseEntity<ReviewerDTO> createReviewer(@RequestBody ReviewerDTO reviewerDTO) {
        ReviewerDTO savedReviewer = reviewerService.createReviewer(reviewerDTO);
        return new ResponseEntity<>(savedReviewer, HttpStatus.CREATED);
    }

    // Get all reviewers
    @GetMapping
    public ResponseEntity<List<ReviewerDTO>> getAllReviewers() {
        List<ReviewerDTO> reviewers = reviewerService.getAllReviewers();
        return new ResponseEntity<>(reviewers, HttpStatus.OK);
    }

    // Get a reviewer by ID
    @GetMapping("/{id}")
    public ResponseEntity<ReviewerDTO> getReviewerById(@PathVariable Long id) {
        Optional<ReviewerDTO> reviewer = reviewerService.getReviewerById(id);
        return reviewer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a reviewer by ID
    @PutMapping("/{id}")
    public ResponseEntity<ReviewerDTO> updateReviewer(@PathVariable Long id, @RequestBody ReviewerDTO reviewerDetails) {
        ReviewerDTO updatedReviewer = reviewerService.updateReviewer(id, reviewerDetails);
        if (updatedReviewer != null) {
            return new ResponseEntity<>(updatedReviewer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a reviewer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewer(@PathVariable Long id) {
        reviewerService.deleteReviewer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
