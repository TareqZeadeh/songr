package com.example.songr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
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

    Album Albums[] = {new Album("sing a song 1", "singer one", 2000, 213, "https://e7.pngegg.com/pngimages/678/968/png-clipart-song-cartoon-singing-singing-child-food-thumbnail.png")
            , new Album("sing a song 2", "singer two", 1500, 190, "https://image.freepik.com/free-vector/happy-kids-boy-girl-sing-song-with-teacher_97632-1981.jpg")
            , new Album("sing a song 3", "singer three", 1700, 200, "https://www.cambridgeenglish.org/Images/sing-and-learn-banner.png")};

    @GetMapping("/album")
    public String getAlbums(Model model) {
        model.addAttribute("Albums", Albums);
        return "Albums";

    }


}
