package com.p1.service;

import com.p1.dao.SongMapper;
import com.p1.exception.song.SongNotFound;
import com.p1.pojo.Song;
import com.p1.pojo.SongExample;
import com.p1.service.inter.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("songServiceImpl")
public class SongServiceImpl implements SongService {
    @Autowired
    SongMapper songMapper;

    public void setSongMapper(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    /**
     * rid：歌曲rid
     *
     * 查询前检查歌曲存在
     * */
    @Override
    public Song selectSongByRid(int rid) throws SongNotFound{
        Song song =songMapper.selectByPrimaryKey(rid);
        if (song==null){
            throw new SongNotFound(rid);
        }
        return song;
    }

    /**
     * text：查询文本
     *
     * 对歌名、歌手、专辑进行模糊查询
     * */
    @Override
    public List<Song> searchSong(String text) {

        List<Song> list=new ArrayList<>();
        SongExample songExample=new SongExample();

        songExample.createCriteria().andSongNameLike("%"+text+"%");
        list.addAll(songMapper.selectByExample(songExample));
        songExample.clear();

        songExample.createCriteria().andArtistLike("%"+text+"%");
        list.addAll(songMapper.selectByExample(songExample));
        songExample.clear();

        songExample.createCriteria().andAlbumLike("%"+text+"%");
        list.addAll(songMapper.selectByExample(songExample));
        songExample.clear();

        //不知道怎么在json里把字段url去掉(T⌓T)
        //将url滞空
        for (Song song:list){
            song.setUrl("");
        }

        return list;
    }
}
