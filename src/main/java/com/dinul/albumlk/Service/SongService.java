package com.dinul.albumlk.Service;
import com.dinul.albumlk.Entity.Song;
import com.dinul.albumlk.Repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    // Method to retrieve all songs
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    // Method to retrieve a song by its ID
    public Song getSongById(Integer id) {
        Optional<Song> song = songRepository.findById(id);
        return song.orElse(null);
    }

    // Method to create a new song
    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    // Method to update an existing song
    public Song updateSong(Integer id, Song songDetails) {
        Song existingSong = songRepository.findById(id).orElse(null);
        if (existingSong != null) {
            existingSong.setTitle(songDetails.getTitle());
            existingSong.setSongNumber(songDetails.getSongNumber());
            existingSong.setAlbum(songDetails.getAlbum());
            return songRepository.save(existingSong);
        }
        return null;
    }

    // Method to delete a song by its ID
    public void deleteSong(Integer id) {
        songRepository.deleteById(id);
    }
}