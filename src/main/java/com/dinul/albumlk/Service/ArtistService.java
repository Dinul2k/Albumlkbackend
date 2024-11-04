package com.dinul.albumlk.Service;

import com.dinul.albumlk.DTO.AlbumDTO;
import com.dinul.albumlk.DTO.ArtistDTO;
import com.dinul.albumlk.Entity.Album;
import com.dinul.albumlk.Entity.Artist;
import com.dinul.albumlk.Repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    // Convert Artist entity to ArtistDTO
    public ArtistDTO convertToDTO(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setUsername(artist.getUsername());
        dto.setEmail(artist.getEmail());
        dto.setName(artist.getName());
        dto.setGenre(artist.getGenre());
        dto.setPassword(artist.getPassword());

        // Convert each Album to AlbumDTO
        dto.setAlbums(
                artist.getAlbums() != null
                        ? artist.getAlbums().stream().map(this::convertAlbumToDTO).collect(Collectors.toList())
                        : Collections.emptyList()
        );

        return dto;
    }
    // Helper method to convert Album to AlbumDTO
    private AlbumDTO convertAlbumToDTO(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setTitle(album.getTitle());
        albumDTO.setReleaseDate(album.getReleaseDate());
        albumDTO.setGenre(album.getGenre());

        // Add any other fields as needed
        return albumDTO;
    }

    // Convert ArtistDTO to Artist entity
    private Artist convertToEntity(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        artist.setUsername(artistDTO.getUsername());
        artist.setEmail(artistDTO.getEmail());
        artist.setName(artistDTO.getName());
        artist.setGenre(artistDTO.getGenre());
        artist.setPassword(artistDTO.getPassword()); // Set password if present in DTO
        return artist;
    }

    // Get all artists
    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get an artist by ID
    public ArtistDTO getArtistById(Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        return artist.map(this::convertToDTO).orElse(null); // Return ArtistDTO if found, else null
    }

    // Create a new artist
    public ArtistDTO createArtist(ArtistDTO artistDTO) {
        Artist artist = convertToEntity(artistDTO);
        Artist savedArtist = artistRepository.save(artist);
        return convertToDTO(savedArtist);
    }

    // Update an artist
    public ArtistDTO updateArtist(Long id, ArtistDTO artistDTO) {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if (optionalArtist.isPresent()) {
            Artist artist = optionalArtist.get();
            artist.setUsername(artistDTO.getUsername());
            artist.setEmail(artistDTO.getEmail());
            artist.setName(artistDTO.getName());
            artist.setGenre(artistDTO.getGenre());

            // Only update password if a new password is provided in DTO
            if (artistDTO.getPassword() != null && !artistDTO.getPassword().isEmpty()) {
                artist.setPassword(artistDTO.getPassword());
            }

            Artist updatedArtist = artistRepository.save(artist);
            return convertToDTO(updatedArtist);
        } else {
            return null; // Artist not found
        }
    }

    // Delete an artist
    public boolean deleteArtist(Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            artistRepository.delete(artist.get());
            return true;
        } else {
            return false;
        }
    }
}
