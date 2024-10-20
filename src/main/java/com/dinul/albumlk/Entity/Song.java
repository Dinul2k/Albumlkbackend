package com.dinul.albumlk.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(name = "song_number", nullable = false)
    private Integer songNumber; // New field to represent the track number

    // Many-to-One relationship with Album
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    // Constructors
    public Song() {
    }

    public Song(String title, Integer songNumber, Album album) {
        this.title = title;
        this.songNumber = songNumber;
        this.album = album;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}