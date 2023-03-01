package com.p1.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p1.dao.DownloadHistoryMapper;
import com.p1.pojo.DownloadHistory;
import com.p1.pojo.DownloadHistoryAndSong;
import com.p1.pojo.DownloadHistoryExample;
import com.p1.pojo.Song;
import com.p1.service.inter.DownloadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;


@Service("downloadHistoryServiceImpl")
public class DownloadHistoryServiceImpl implements DownloadHistoryService {

    @Autowired
    DownloadHistoryMapper downloadHistoryMapper;

    @Autowired
    @Qualifier("songServiceImpl")
    SongServiceImpl service;

    @Override
    public int insertDownloadHistory(int userId,int rid) {
        DownloadHistory downloadHistory=new DownloadHistory();
        downloadHistory.setRid(rid);
        downloadHistory.setUserId(userId);
        return downloadHistoryMapper.insertSelective(downloadHistory);
    }

    @Override
    public int deleteDownloadHistoryByDownloadHistoryId(int downloadHistoryId) {
        return downloadHistoryMapper.deleteByPrimaryKey(downloadHistoryId);
    }

    @Override
    public DownloadHistory selectDownloadHistoryById(int downloadHistoryId) {
        return downloadHistoryMapper.selectByPrimaryKey(downloadHistoryId);
    }

    /**
     * isFav：要更改为的收藏状态
     * downloadHistoryId：记录id
     *
     * 修改收藏状态
     * */
    @Override
    public int updateFav(int isFav,int downloadHistoryId) {
        DownloadHistory downloadHistory =new DownloadHistory();
        downloadHistory.setDownloadHistoryId(downloadHistoryId);
        if (isFav==DownloadHistory.IS_FAV){
            downloadHistory.setFav(DownloadHistory.IS_FAV);
        }else {
            downloadHistory.setFav(DownloadHistory.NO_FAV);
        }
        return downloadHistoryMapper.updateByPrimaryKeySelective(downloadHistory);
    }

    /**
     * userId：用户id
     * index：页码
     *
     * 查询对应用户记录，并返回页码等数据（包装在DownloadHistoryAndSong、PageInfo内）
     * */
    @Override
    public PageInfo<DownloadHistoryAndSong> gainSongByPage(int userId, int index) {

        PageHelper.startPage(index,DownloadHistoryService.PAGE_SIZE);
        List<DownloadHistoryAndSong> downloadHistoryAndSongList=downloadHistoryMapper.selectDownloadHistoryAndSongByRid(userId);

        return new PageInfo<>(downloadHistoryAndSongList);
    }

    /**
     * 删除list中所有记录
     * */
    @Override
    public void deleteDownloadHistoryByList(List<Integer> list){
        for (int id:list){
            downloadHistoryMapper.deleteByPrimaryKey(id);
        }
    }

}
    

