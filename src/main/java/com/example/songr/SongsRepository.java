package com.example.songr;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongsRepository extends JpaRepository<Songs, Long> {

    Optional <List<Songs>> findAllByAlbum_Title(String title);
}
