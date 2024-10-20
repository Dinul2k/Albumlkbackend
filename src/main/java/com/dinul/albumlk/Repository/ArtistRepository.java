package com.dinul.albumlk.Repository;

import com.dinul.albumlk.Entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository  extends JpaRepository<Artist, Long> {
}
