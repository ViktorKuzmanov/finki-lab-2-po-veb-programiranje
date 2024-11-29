package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        // TODO: Komentiran kod - nz dali kje mi treba
//        if (error != null && !error.isEmpty()) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", error);
//        }
        model.addAttribute("songs", this.songService.listSongs());
        return "listSongs";
    }
}
