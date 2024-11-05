package com.dinul.albumlk.Service;

import com.dinul.albumlk.DTO.CommentDTO;
import com.dinul.albumlk.DTO.ReviewerDTO;
import com.dinul.albumlk.Entity.Reviewer;
import com.dinul.albumlk.Repository.ReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewerService {

    private final ReviewerRepository reviewerRepository;

    @Autowired
    public ReviewerService(ReviewerRepository reviewerRepository) {
        this.reviewerRepository = reviewerRepository;
    }

    // Method to create a new reviewer
    public ReviewerDTO createReviewer(ReviewerDTO reviewerDTO) {
        Reviewer reviewer = convertDTOToReviewer(reviewerDTO);
        reviewer.setComments(new ArrayList<>()); // Initialize comments list
        Reviewer savedReviewer = reviewerRepository.save(reviewer);
        return convertReviewerToDTO(savedReviewer);
    }

    // Method to get all reviewers
    public List<ReviewerDTO> getAllReviewers() {
        List<Reviewer> reviewers = reviewerRepository.findAll();
        return reviewers.stream()
                .map(this::convertReviewerToDTO)
                .collect(Collectors.toList());
    }

    // Method to get a reviewer by ID
    public Optional<ReviewerDTO> getReviewerById(Long id) {
        Optional<Reviewer> reviewer = reviewerRepository.findById(Math.toIntExact(id));
        return reviewer.map(this::convertReviewerToDTO);
    }

    // Method to update a reviewer
    public ReviewerDTO updateReviewer(Long id, ReviewerDTO updatedReviewerDTO) {
        Optional<Reviewer> optionalReviewer = reviewerRepository.findById(Math.toIntExact(id));
        if (optionalReviewer.isPresent()) {
            Reviewer reviewer = optionalReviewer.get();
            reviewer.setUsername(updatedReviewerDTO.getUsername());
            reviewer.setEmail(updatedReviewerDTO.getEmail());
            reviewer.setPassword(updatedReviewerDTO.getPassword()); // Temporarily set password
            reviewer = reviewerRepository.save(reviewer);
            return convertReviewerToDTO(reviewer);
        } else {
            return null; // or throw a custom exception if the reviewer is not found
        }
    }

    // Method to delete a reviewer by ID
    public void deleteReviewer(Long id) {
        reviewerRepository.deleteById(Math.toIntExact(id));
    }

    // Convert Reviewer to ReviewerDTO
    private ReviewerDTO convertReviewerToDTO(Reviewer reviewer) {
        ReviewerDTO dto = new ReviewerDTO();
        dto.setId(reviewer.getId());
        dto.setUsername(reviewer.getUsername());
        dto.setEmail(reviewer.getEmail());
        dto.setPassword(reviewer.getPassword()); // Temporarily include password in DTO

        // Check if comments are null and handle accordingly
        dto.setComments(reviewer.getComments() != null ?
                reviewer.getComments().stream()
                        .map(comment -> new CommentDTO(comment.getId(), comment.getContent(), comment.getAlbum().getId()))
                        .collect(Collectors.toList()) :
                new ArrayList<>()); // Return an empty list if comments are null

        return dto;
    }

    // Convert ReviewerDTO to Reviewer
    private Reviewer convertDTOToReviewer(ReviewerDTO dto) {
        Reviewer reviewer = new Reviewer();
        reviewer.setId(dto.getId());
        reviewer.setUsername(dto.getUsername());
        reviewer.setEmail(dto.getEmail());
        reviewer.setPassword(dto.getPassword()); // Temporarily set password
        return reviewer;
    }
}
