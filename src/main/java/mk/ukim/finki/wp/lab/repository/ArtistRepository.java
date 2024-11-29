package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ArtistRepository {

    private List<Artist> artists = new ArrayList<>();

    @PostConstruct
    private void initializeData() {
        for (int i = 0; i < 5; i++) {
            Artist artist = new Artist((long) i, "ImeA" + i, "PrezimeA" + i, "BioA" + i);
            artists.add(artist);
        }
    }

    public List<Artist> findAll() {
        return artists;
    }

    public Optional<Artist> findById(Long id) {
        for(Artist a : artists) {
            // TODO: Compare them the right way , we can't use == this is for reference comparison only
            if(Objects.equals(a.getId(), id)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

}
