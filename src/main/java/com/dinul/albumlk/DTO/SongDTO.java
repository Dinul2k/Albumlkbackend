package com.dinul.albumlk.DTO;

public class SongDTO {

    private Integer id;
    private String title;
    private Integer songNumber;
    private Long albumId; // Use Long for albumId

    // Constructors
    public SongDTO() {
    }

    public SongDTO(Integer id, String title, Integer songNumber, Long albumId) {
        this.id = id;
        this.title = title;
        this.songNumber = songNumber;
        this.albumId = albumId;
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

    public Integer getSongNumber() {
        return songNumber;
    }

    public void setSongNumber(Integer songNumber) {
        this.songNumber = songNumber;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}