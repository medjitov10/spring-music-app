package com.ga.service;

import com.ga.dao.SongDao;
import com.ga.dao.SongDaoImpl;
import com.ga.entity.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SongServiceTest {

    @Mock
    SongDao songDao;

    @InjectMocks
    private SongServiceImpl songService;

    @InjectMocks
    private Song song;

    @Test
    public void addSong_Song_Success() throws Exception {
        Song song = new Song();
        song.setId(9);
        song.setArtist("Nyamano");
        song.setTitle("Wandering all night");
        song.setLength(4.20);
        System.out.println(song.toString());

        when(songDao.createSong(any())).thenReturn(song);

        Song tempSong = songService.addSong(song);

        System.out.println(song.toString());
        System.out.println(tempSong.toString());
//        assertEquals(expected, actual);
        assertEquals(tempSong.getArtist(), song.getArtist());
        assertEquals(tempSong.getTitle(), song.getTitle());
        assertEquals(tempSong.getLength(), song.getLength());
    }
    @Test
    public void deleteSong_Song_Success() throws Exception {
        Song song = new Song();
        song.setId(9);
        song.setArtist("Nyamano");
        song.setTitle("Wandering all night");
        song.setLength(4.20);
        System.out.println(song.toString());

        when(songDao.deleteSong(9)).thenReturn(song);
        Song deletedSong = songDao.deleteSong(9);

        assertEquals(song, deletedSong);

    }
}
