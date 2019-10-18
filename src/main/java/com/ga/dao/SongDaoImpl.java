package com.ga.dao;

import com.ga.entity.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SongDaoImpl implements SongDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Song createSong(Song song) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            session.save(song);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return song;
    }

    @Override
    public Song deleteSong(int songId) {
        Song foundSong = null;
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            foundSong = session.get(Song.class, songId);
            session.delete(foundSong);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return foundSong;
    }
}
