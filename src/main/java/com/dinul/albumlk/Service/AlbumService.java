package com.dinul.albumlk.Service;

import com.dinul.albumlk.DTO.AlbumDTO;
import com.dinul.albumlk.Entity.Album;
import com.dinul.albumlk.Repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    // Convert Album to AlbumDTO
    public AlbumDTO convertToDTO(Album album) {
        List<Integer> songIds = album.getSongs().stream()
                .map(song -> song.getId())
                .collect(Collectors.toList());

        return new AlbumDTO(
                album.getId(),
                album.getTitle(),
                album.getReleaseDate(),
                album.getGenre(),
                album.getArtist() != null ? album.getArtist().getId() : null,
                songIds
        );
    }

    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AlbumDTO getAlbumById(Long id) {
        Album album = albumRepository.findById(id).orElse(null);
        return album != null ? convertToDTO(album) : null;
    }



    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Album updateAlbum(Long id, Album albumDetails) {
        Album existingAlbum = albumRepository.findById(id).orElse(null);
        if (existingAlbum != null) {
            existingAlbum.setTitle(albumDetails.getTitle());
            existingAlbum.setReleaseDate(albumDetails.getReleaseDate());
            existingAlbum.setGenre(albumDetails.getGenre());
            return albumRepository.save(existingAlbum);
        }
        return null;
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
