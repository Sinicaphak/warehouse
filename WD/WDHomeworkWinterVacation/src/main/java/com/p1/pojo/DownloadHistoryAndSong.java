package com.p1.pojo;
/**
 * Song和DownloadHistory的查询结果对应类
 * */
public class DownloadHistoryAndSong extends Song{
    private int fav;
    private int id;

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
