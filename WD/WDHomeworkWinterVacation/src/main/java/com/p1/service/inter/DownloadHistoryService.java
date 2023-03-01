package com.p1.service.inter;

import com.github.pagehelper.PageInfo;
import com.p1.pojo.DownloadHistory;
import com.p1.pojo.Song;

import java.util.List;

public interface DownloadHistoryService {

    /**
     * 每一页5条信息
     * */
    public static final int PAGE_SIZE=5;

    int insertDownloadHistory(int userId,int rid);

    int deleteDownloadHistoryByDownloadHistoryId(int downloadHistoryId);

    DownloadHistory selectDownloadHistoryById(int downloadHistoryId);

    int updateFav(int isFav,int downloadHistoryId);

    PageInfo<Song> gainSongByPage(int userId, int pageIndex);

    void deleteDownloadHistoryByList(List<Integer> list);
}
