package com.dinul.albumlk.Repository;
import com.dinul.albumlk.Entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
}
