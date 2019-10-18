package com.ga.service;

import com.ga.entity.Song;

public interface SongService {

   public Song addSong(Song song);
   public Song deleteSong(int songId);
}
