package com.dinul.albumlk.Service;
import com.dinul.albumlk.Entity.Artist;
import com.dinul.albumlk.Repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    // Get all artists
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    // Get an artist by ID
    public Artist getArtistById(Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        return artist.orElse(null); // Return artist if found, else return null
    }

    // Create a new artist
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    // Update an artist
    public Artist updateArtist(Long id, Artist artistDetails) {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if (optionalArtist.isPresent()) {
            Artist artist = optionalArtist.get();
            artist.setUsername(artistDetails.getUsername());
            artist.setPassword(artistDetails.getPassword());
            artist.setEmail(artistDetails.getEmail());
            artist.setName(artistDetails.getName());
            artist.setGenre(artistDetails.getGenre());
            // Update any other necessary fields
            return artistRepository.save(artist);
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