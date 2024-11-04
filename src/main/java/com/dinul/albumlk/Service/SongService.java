package com.dinul.albumlk.Service;

import com.dinul.albumlk.DTO.SongDTO;
import com.dinul.albumlk.Entity.Album;
import com.dinul.albumlk.Entity.Song;
import com.dinul.albumlk.Repository.AlbumRepository;
import com.dinul.albumlk.Repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    // Method to retrieve all songs
    public List<SongDTO> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        return songs.stream()
                .map(this::convertSongToDTO)
                .collect(Collectors.toList());
    }

    // Method to retrieve a song by its ID
    public SongDTO getSongById(Integer id) {
        Optional<Song> song = songRepository.findById(id);
        return song.map(this::convertSongToDTO).orElse(null);
    }

    // Method to create a new song
    public SongDTO createSong(SongDTO songDTO) {
        Song song = convertDTOToSong(songDTO);
        Song savedSong = songRepository.save(song);
        return convertSongToDTO(savedSong);
    }

    // Method to update an existing song
    public SongDTO updateSong(Integer id, SongDTO songDTO) {
        Optional<Song> existingSongOpt = songRepository.findById(id);
        if (existingSongOpt.isPresent()) {
            Song existingSong = existingSongOpt.get();
            existingSong.setTitle(songDTO.getTitle());
            existingSong.setSongNumber(songDTO.getSongNumber());

            // Set album if provided in songDTO
            if (songDTO.getAlbumId() != null) {
                Optional<Album> albumOpt = albumRepository.findById(songDTO.getAlbumId());
                albumOpt.ifPresent(existingSong::setAlbum);
            } else {
                existingSong.setAlbum(null); // Clear album if not specified in DTO
            }

            Song updatedSong = songRepository.save(existingSong);
            return convertSongToDTO(updatedSong);
        }
        return null;
    }

    // Method to delete a song by its ID
    public void deleteSong(Integer id) {
        songRepository.deleteById(id);
    }

    // Helper method to convert Song entity to SongDTO
    private SongDTO convertSongToDTO(Song song) {
        return new SongDTO(
                song.getId(),
                song.getTitle(),
                song.getSongNumber(),
                song.getAlbum() != null ? song.getAlbum().getId() : null
        );
    }

    // Helper method to convert SongDTO to Song entity
    private Song convertDTOToSong(SongDTO songDTO) {
        Song song = new Song();
        song.setTitle(songDTO.getTitle());
        song.setSongNumber(songDTO.getSongNumber());

        // Set album based on albumId in DTO
        if (songDTO.getAlbumId() != null) {
            Optional<Album> albumOpt = albumRepository.findById(songDTO.getAlbumId());
            albumOpt.ifPresent(song::setAlbum);
        }

        return song;
    }
}
