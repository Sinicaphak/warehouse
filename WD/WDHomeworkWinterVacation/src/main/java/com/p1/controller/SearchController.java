package com.p1.controller;


import com.p1.common.Result;
import com.p1.common.TokenUnit;
import com.p1.pojo.Song;
import com.p1.service.inter.DownloadHistoryService;
import com.p1.service.inter.SearchHistoryService;
import com.p1.service.inter.SongService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;


@Controller
@ComponentScan("service")
public class SearchController {

    @Autowired
    @Qualifier("songServiceImpl")
    SongService songService;

    @Autowired
    @Qualifier("downloadHistoryServiceImpl")
    DownloadHistoryService downloadHistoryService;

    @Autowired
    @Qualifier("searchHistoryServiceImpl")
    SearchHistoryService searchHistoryService;

    @ResponseBody
    @GetMapping("/search")
    public Result search(@RequestHeader("Authorization") String token, @PathVariable String text) {
        int userId = TokenUnit.gainUserIdByToken(token);
        try {
            return searchRecord(userId, text);
        } catch (Exception e) {
            return Result.gainNotFound();
        }
    }

    @GetMapping("search/download/{var}")
    public Result download(@RequestHeader("Authorization") String token, @PathVariable("var") String var,HttpServletResponse response) throws IOException {
        int userId = TokenUnit.gainUserIdByToken(token);
        /**
         * 利用正则表达式提取rid....我只能想到这种办法了
         * */
        int rid=Integer.parseInt(var.replace(":", ""));

        Song song = songService.selectSongByRid(rid);
        URL url = new URL(song.getUrl());

        /**
         * 获取文件输出流
         * */
        InputStream inputStream = url.openStream();
        response.reset();
        response.setContentType("application/octet-stream");
        String filename = song.getSongName() + ".mp3";
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();

        downloadHistoryService.insertDownloadHistory(userId, rid);

        return Result.gainSuccess();
    }

    /**
     * 获取信息并保存搜索记录
     * */
    Result searchRecord(int userId, String text) {
        /**
         * 返回数据
         * */
        List<Song> list = songService.searchSong(text);
        Result result = Result.gainSuccess();
        result.putObject("list", list);

        /**
         * 记录搜索历史
         * */
        for (Song song : list) {
            song.getRid();
            searchHistoryService.insertSearchHistory(userId, song.getRid());
        }
        return result;
    }
}
