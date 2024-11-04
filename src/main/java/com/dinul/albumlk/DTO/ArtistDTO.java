package com.dinul.albumlk.DTO;

import java.util.List;

public class ArtistDTO {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String genre;
    private String password;
    private List<AlbumDTO> albums; // Assuming AlbumDTO is defined similarly

    // Constructors
    public ArtistDTO() {}

    public ArtistDTO(Long id, String username, String email, String name, String genre, String password, List<AlbumDTO> albums) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.genre = genre;
        this.password = password;
        this.albums = albums;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AlbumDTO> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumDTO> albums) {
        this.albums = albums;
    }
}
