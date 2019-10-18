package com.ga.service;

import com.ga.dao.SongDao;
import com.ga.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongDao songDao;

    @Override
    public Song addSong(Song song) {
        return songDao.createSong(song);
    }

    @Override
    public Song deleteSong(int songId) {
        return songDao.deleteSong(songId);
    }
}
