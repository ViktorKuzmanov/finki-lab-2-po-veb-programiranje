package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class SongRepository {

    private List<Song> songs = new ArrayList<>();
    private final AlbumRepository albumRepository;

    public SongRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @PostConstruct
    private void initializeData() {
        for (int i = 0; i < 5; i++) {
            Song song = new Song(String.valueOf(i + 1), "ImeS" + i, "ZanrS" + i, i, albumRepository.findAll().get(i));
            songs.add(song);

        }
    }

    public List<Song> findAll() {
        return songs;
    }

    public Song findByTrackId(String trackId) {
        for (Song s : songs) {
            // learn: tuka porazlicno go pravam ne e so Objects tuku so equals
            if (s.getTrackId().equals(trackId)) {
                return s;
            }
        }
        return null;
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        return artist;
    }

    public Song save(String trackId, String title, String genre, Integer releaseYear, Album album){
        Song song = new Song(trackId, title, genre, releaseYear, album);
        songs.add(song);
        return song;
    }

    public void deleteById(Long id) {
        songs.removeIf(s -> Objects.equals(s.getId(), id));
    }

    public Song findById(Long Id) {
        for (Song s : songs) {
            if (Objects.equals(s.getId(), Id)) {
                return s;
            }
        }
        return null;
    }
}
