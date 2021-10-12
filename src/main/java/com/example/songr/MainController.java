package com.example.songr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private AlbumRepository albumRepository;

    private final SongsRepository songsRepository;
    public MainController(SongsRepository songsRepository){
        this.songsRepository=songsRepository;
    }

    @GetMapping("/hello")
    public String helloWorld(@RequestParam(name = "name", required = false, defaultValue = "world") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/capitalize/{word}")
    public String capitalize(@PathVariable String word, Model model) {
        model.addAttribute("word", word);
        return "capitalize";
    }

    Album[] Albums = {new Album("sing a song 1", "singer one", 2000, 213, "https://e7.pngegg.com/pngimages/678/968/png-clipart-song-cartoon-singing-singing-child-food-thumbnail.png")
            , new Album("sing a song 2", "singer two", 1500, 190, "https://image.freepik.com/free-vector/happy-kids-boy-girl-sing-song-with-teacher_97632-1981.jpg")
            , new Album("sing a song 3", "singer three", 1700, 200, "https://www.cambridgeenglish.org/Images/sing-and-learn-banner.png")};

    @GetMapping("/album")
    public String getAlbum(Model model) {
        model.addAttribute("Albums", Albums);
        return "Albums";

    }
    //======================lab 12=============================
    @GetMapping("/albums")
    public String getAlbums(Model model){
        model.addAttribute("Albums", albumRepository.findAll());
        return "AlbumForm";
    }
    @PostMapping("/albums")
    public RedirectView addNewAlbum(@ModelAttribute Album album){
        albumRepository.save(album);
        return new RedirectView("/songs/newSong");
    }

    @GetMapping("/allAlbums")
    public String getAllAlbums(Model model) {
        model.addAttribute("Albums", albumRepository.findAll());

        return "Albums";
    }
    //======================lab 13=============================
    @GetMapping("/songs")
    public String getAllSongs(Model model){
        List<Songs> songs=songsRepository.findAll();
        model.addAttribute("songs",songs);
        return "Songs";
    }
    @GetMapping("/songs/newSong")
    public String addSongPage(Model model){
//        model.addAttribute("Songs",songsRepository.findAll());
        return "SongForm";
    }
    @PostMapping("/song")
    public RedirectView addSong(@ModelAttribute SongsDto songDto){
        Album album = albumRepository.findAuthorByTitle(songDto.getAlbum()).orElseThrow();
        Songs song = new Songs(songDto.getTitle(),songDto.getLength(),songDto.getTrackNumber(),album);
    songsRepository.save(song);
    return new RedirectView("songs");
    }
    @GetMapping("/songs/album/{album}")
    public String getAlbumSongs(@PathVariable String album, Model model){
        List<Songs> songs = songsRepository.findAllByAlbum_Title(album).orElseThrow();
        model.addAttribute("songs", songs);
        return "Songs";
    }



}
