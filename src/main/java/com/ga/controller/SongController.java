package com.ga.controller;

import com.ga.entity.Song;
import com.ga.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    // TODO: Remove map-meth after test
    @GetMapping("/hello")
    public String hello() {
        return "Song Controller Sanity Check";
    }


    @PostMapping
    public Song createSong(@RequestBody Song song){
        return songService.addSong(song);
    }

    @DeleteMapping("/{songId}/delete")
    public Song deleteSong(@PathVariable int songId) {
    	return songService.deleteSong(songId);
    }
}
