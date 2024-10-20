package com.dinul.albumlk.Service;

import com.dinul.albumlk.Entity.Album;
import com.dinul.albumlk.Repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Album getAlbumById(Integer id) {
        return albumRepository.findById(id).orElse(null);
    }

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Album updateAlbum(Integer id, Album albumDetails) {
        Album existingAlbum = albumRepository.findById(id).orElse(null);
        if (existingAlbum != null) {
            existingAlbum.setTitle(albumDetails.getTitle());
            existingAlbum.setReleaseDate(albumDetails.getReleaseDate());
            existingAlbum.setGenre(albumDetails.getGenre());
            return albumRepository.save(existingAlbum);
        }
        return null;
    }

    public void deleteAlbum(Integer id) {
        albumRepository.deleteById(id);
    }
}
