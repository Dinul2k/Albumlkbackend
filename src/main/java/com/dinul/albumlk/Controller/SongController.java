package com.dinul.albumlk.Controller;

import com.dinul.albumlk.DTO.SongDTO;
import com.dinul.albumlk.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    // Get all songs
    @GetMapping
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs();
    }

    // Get song by ID
    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Integer id) {
        SongDTO songDTO = songService.getSongById(id);
        if (songDTO != null) {
            return ResponseEntity.ok(songDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new song
    @PostMapping
    public ResponseEntity<SongDTO> createSong(@RequestBody SongDTO songDTO) {
        SongDTO newSong = songService.createSong(songDTO);
        return ResponseEntity.ok(newSong);
    }

    // Update an existing song
    @PutMapping("/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Integer id, @RequestBody SongDTO songDTO) {
        SongDTO updatedSong = songService.updateSong(id, songDTO);
        if (updatedSong != null) {
            return ResponseEntity.ok(updatedSong);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a song by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Integer id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
