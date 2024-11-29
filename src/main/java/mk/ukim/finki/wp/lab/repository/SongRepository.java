package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SongRepository {

    private List<Song> songs = new ArrayList<>();
    private final AlbumRepository albumRepository;

    // TODO: vaka a dodadov albumRepository za da gi pristapam albumite i da initialize album za sekoj pesna
    public SongRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @PostConstruct
    private void initializeData() {
        for (int i = 0; i < 5; i++) {
            // TODO: na straiot kod promeni: tuka dodadov album od kaa go dodadov album deka postoe uopste
            Song song = new Song("TrakaS" + i, "ImeS" + i, "ZanrS" + i, i, albumRepository.findAll().get(i));
            songs.add(song);
        }
    }

    public List<Song> findAll() {
        return songs;
    }

    public Song findByTrackId(String trackId) {
        for (Song s : songs) {
            // TODO: tuka porazlicno go pravam ne e so Objects tuku so equals
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
}
