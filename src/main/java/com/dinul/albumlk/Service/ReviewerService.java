package com.dinul.albumlk.Service;
import com.dinul.albumlk.Entity.Reviewer;
import com.dinul.albumlk.Repository.ReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewerService {

    private final ReviewerRepository reviewerRepository;

    @Autowired
    public ReviewerService(ReviewerRepository reviewerRepository) {
        this.reviewerRepository = reviewerRepository;
    }

    // Method to create a new reviewer
    public Reviewer createReviewer(Reviewer reviewer) {
        return reviewerRepository.save(reviewer);
    }

    // Method to get all reviewers
    public List<Reviewer> getAllReviewers() {
        return reviewerRepository.findAll();
    }

    // Method to get a reviewer by ID
    public Optional<Reviewer> getReviewerById(Integer id) {
        return reviewerRepository.findById(id);
    }

    // Method to update a reviewer
    public Reviewer updateReviewer(Integer id, Reviewer updatedReviewer) {
        Optional<Reviewer> optionalReviewer = reviewerRepository.findById(id);
        if (optionalReviewer.isPresent()) {
            Reviewer reviewer = optionalReviewer.get();
            reviewer.setUsername(updatedReviewer.getUsername());
            reviewer.setPassword(updatedReviewer.getPassword());
            reviewer.setEmail(updatedReviewer.getEmail());
            return reviewerRepository.save(reviewer);
        } else {
            return null; // or throw a custom exception if the reviewer is not found
        }
    }

    // Method to delete a reviewer by ID
    public void deleteReviewer(Integer id) {
        reviewerRepository.deleteById(id);
    }
}
