package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {

    private List<Album> albums = new ArrayList<>();

    @PostConstruct
    private void initializeData() {
        for (int i = 0; i < 5; i++) {
            Album album = new Album((long)(i + 1), "AlbumIme" + (i + 1), "AlbumGenre" + + (i + 1), "" + (i + 1));
            albums.add(album);
        }
    }
    public Optional<Album> findAlbumById(Long albumId) {
        return albums.stream().filter(a -> a.getId().equals(albumId)).findFirst();
    }
    public List<Album> findAll() {
        return albums;
    }
}
