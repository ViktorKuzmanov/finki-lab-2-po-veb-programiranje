package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("songs", this.songService.listSongs());
        return "listSongs";
    }

    @PostMapping("/songs/add")
    // TODO: tuka dane treba required = false na nekoj parametar ili na site?
    public String saveSong(@RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam Integer releaseYear,
                           @RequestParam Long albumId){
        // TODO: treba greska da frlam ako ne najde album spored albumId?
        Optional<Album> a = this.albumService.findById(albumId);
        Album album = null;
        if(a.isPresent()){
            album = a.get();
        }
        Long novoId = (long)this.songService.listSongs().size() + 1;
        System.out.println("v1 ==" + novoId);
        this.songService.save(novoId, trackId, title, genre, releaseYear, album);
        return "redirect:/songs";
    }

    @GetMapping("/songs/add-form")
    public String getAddSongPage(Model model){
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @GetMapping("/songs/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model){
        // TODO: mora greska da frlam tuka ako mi pratat id so ne postoe
        Song song = this.songService.findById(id);
        System.out.print("aaa" + song.getTitle());
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("song", song);
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @PostMapping("/songs/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam Integer releaseYear,
                           @RequestParam Long albumId){

        Song song = this.songService.findById(songId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setTrackId(trackId);
        song.setReleaseYear(releaseYear);
        Optional<Album> a = this.albumService.findById(albumId);
        Album album = null;
        if(a.isPresent()){
            album = a.get();
        }
        song.setAlbum(album);
        return "redirect:/songs";
    }

    @GetMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }
}
