package com.ga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.entity.Song;
import com.ga.service.SongService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SongControllerTest {

    private MockMvc mockMvc;

    private Song song;

    @InjectMocks
    SongController songController;

    @Mock
    SongService songService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(songController).build();

         Song song = new Song();
    }

    @Test
    public void addSong_Song_Success() throws Exception {
        Song song = new Song();
        song.setId(9);
        song.setArtist("Nyamano");
        song.setTitle("Wandering all night");
        song.setLength(4.20);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/song")
            .contentType(MediaType.APPLICATION_JSON)
            .content(createSongInJson("Wandering all night","Nyamano", 4.20));

        when(songService.addSong(any())).thenReturn(song);

        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(content().json("{\"title\":\"Wandering all night\",\"artist\":\"Nyamano\",\"length\":4.2,\"id\":9}"))
            .andReturn();


    }

    // TODO: need to figure out why error returns 200 Status Code
//    @Test
//    public void addSong_Song_Error() throws Exception {
//        Song song = new Song();
//        song.setId(9);
//        song.setArtist(null);
//        song.setTitle("Wandering all night");
//        song.setLength(4.20);
//        System.out.println(song);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/song")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(createSongInJson("Wandering all night",null, 4.20));
//        ReponseEntity<> response = ResponseEntity.badRequest();
//        ResponseEntity<?> response = new ResponseEntity(HttpStatus.BAD_REQUEST);
//        when(songService.addSong(any())).thenReturn(response);
//        when(songService.addSong(any())).thenReturn(song);
//
//        mockMvc.perform(requestBuilder)
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("API error: new Song could not be added"))
//                .andReturn();
//    }

    @Test
    public void deleteSong() throws Exception {
        int songId = 9;
        Song song = new Song();
        song.setId(9);
        song.setArtist("Nyamano");
        song.setTitle("Wandering all night");
        song.setLength(4.20);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/song/9/delete");

        when(songService.deleteSong(songId)).thenReturn(song);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"Wandering all night\",\"artist\":\"Nyamano\",\"length\":4.2,\"id\":9}"))
                .andReturn();
    // NOTE: is there a better way to do this?
    }

    private static String createSongInJson(String title, String artist, double length) {
        return  "{ \"title\": \"" + title + "\", " +
                "\"artist\": \"" + artist + "\", " +
                "\"length\":\"" + length + "\"}";
    }
}
