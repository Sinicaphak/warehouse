package com.p1.exception.song;

import com.p1.exception.BaseException;
import com.p1.pojo.Song;

public class SongNotFound extends BaseException {
    private int rid;

    public SongNotFound(int rid) {
        this.rid = rid;
    }
    @Override
    public String toString() {
        return this.getClass().getName()+"：找不到歌曲，rid为{"+rid+"}";
    }
}
