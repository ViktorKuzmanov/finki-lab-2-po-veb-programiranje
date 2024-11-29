package mk.ukim.finki.wp.lab.service;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public Song save(Long id, String trackId, String title, String genre, Integer releaseYear, Album album) {
        return songRepository.save(id, trackId, title, genre, releaseYear, album);
    }
    @Override
    public Song findById(Long Id) {
        return songRepository.findById(Id);
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }
}
