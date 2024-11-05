package com.dinul.albumlk.DTO;


import java.util.List;

public class ReviewerDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<CommentDTO> comments; // Assuming a CommentDTO exists

    // Constructors
    public ReviewerDTO() {
    }

    public ReviewerDTO(Long id, String username, String email,String password, List<CommentDTO> comments) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.comments = comments;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}