package com.p1.service.inter;

import com.p1.pojo.Song;

import java.util.List;

public interface SongService {
    Song selectSongByRid(int rid);
    List<Song> searchSong(String text);

}
