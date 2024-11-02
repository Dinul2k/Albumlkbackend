package com.dinul.albumlk.DTO;

import java.time.LocalDate;
import java.util.List;

public class AlbumDTO {

    private Integer id;
    private String title;
    private LocalDate releaseDate;
    private String genre;
    private Long artistId;          // Only include artist ID to avoid full Artist object
    private List<Integer> songIds;     // Only include song IDs to avoid full Song objects

    // Constructors
    public AlbumDTO() {}

    public AlbumDTO(Integer id, String title, LocalDate releaseDate, String genre, Long artistId, List<Integer> songIds) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.artistId = artistId;
        this.songIds = songIds;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public List<Integer> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Integer> songIds) {
        this.songIds = songIds;
    }
}
