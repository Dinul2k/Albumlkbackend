package com.dinul.albumlk.Controller;

import com.dinul.albumlk.DTO.AlbumDTO;
import com.dinul.albumlk.Entity.Album;
import com.dinul.albumlk.Service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    // Returns a list of AlbumDTOs
    @GetMapping
    public List<AlbumDTO> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    // Returns a single AlbumDTO by ID
    @GetMapping("/{id}")
    public AlbumDTO getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id);
    }

    // Accepts an Album entity and returns the saved AlbumDTO
    @PostMapping
    public AlbumDTO createAlbum(@RequestBody Album album) {
        Album createdAlbum = albumService.createAlbum(album);
        return albumService.convertToDTO(createdAlbum); // Convert to DTO after saving
    }

    // Accepts an Album entity and updates the record, returning the updated AlbumDTO
    @PutMapping("/{id}")
    public AlbumDTO updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        Album updatedAlbum = albumService.updateAlbum(id, album);
        return updatedAlbum != null ? albumService.convertToDTO(updatedAlbum) : null;
    }

    // Deletes an album by ID
    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
    }
}
