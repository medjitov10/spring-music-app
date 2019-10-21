package com.ga.dao;

import com.ga.entity.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SongDaoTest {
    @InjectMocks
    private Song song;

    @InjectMocks
    private SongDaoImpl songDao;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    Session session;

    @Mock
    Transaction transaction;

    @Mock
    Query<Song> query;

    @Before
    public void init(){
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void createSong_Song_Success() {
        Song song = new Song();
        song.setId(1);
        song.setArtist("Shawn Mendes");
        song.setTitle("In My Blood");
        song.setLength(3.31);

        Song savedSong = songDao.createSong(song);
        assertEquals(song, savedSong);
    }

    @Test
    public void deleteSong_Song_Success() {
        Song song = new Song();
        song.setId(1);
        song.setArtist("Shawn Mendes");
        song.setTitle("In My Blood");
        song.setLength(3.31);

        when(session.get(Song.class, 1)).thenReturn(song);

        Song deletedSong = songDao.deleteSong(song.getId());

        assertEquals(song, deletedSong);
    }
}
