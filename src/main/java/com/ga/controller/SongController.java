package com.ga.controller;

import com.ga.entity.Song;
import com.ga.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    @PostMapping
    public ResponseEntity<?> createSong(@RequestBody Song song){
        try {
            return ResponseEntity.ok(songService.addSong(song));
        } catch (Exception e) {
           ResponseEntity<?> response = ResponseEntity.badRequest().body("API error: new Song could not be added");
            System.out.println(response);
           return response;
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
           // TODO: Build out error message into Json Object?
        }

    }

    @DeleteMapping("/{songId}/delete")
    public ResponseEntity<?> deleteSong(@PathVariable int songId) {
        try {
            return ResponseEntity.ok(songService.deleteSong(songId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("API error: song "+ songId +" could not be deleted");
        }
    }
}
