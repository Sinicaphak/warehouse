package com.p1.controller;


import com.p1.common.Result;
import com.p1.common.TokenUnit;
import com.p1.common.UncodingTool;
import com.p1.pojo.Song;
import com.p1.service.inter.DownloadHistoryService;
import com.p1.service.inter.SearchHistoryService;
import com.p1.service.inter.SongService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.*;
import java.util.List;


@Controller
@ComponentScan("service")
@RequestMapping("/search")
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
    @GetMapping
    public Result search(@RequestHeader("Authorization") String token, @RequestParam String text) {
        int userId = TokenUnit.gainUserIdByToken(token);
        try {
            return searchRecord(userId, text);
        } catch (Exception e) {
            return Result.gainNotFound();
        }
    }

    @GetMapping(value = "/download/:{rid}",produces="application/octet-stream")
    public void download(@PathVariable("rid") int rid,HttpServletResponse response) throws IOException {
        Song song = songService.selectSongByRid(rid);
        String fileName = song.getSongName() + ".mp3";
        URL url = new URL(song.getUrl());

        ServletOutputStream servletOutputStream = null;
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
        try {
            response.setHeader("Content-Type", "application/force-download;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    URLEncoder.encode(fileName, "UTF-8"));

            inputStream = httpURLConnection.getInputStream();
            servletOutputStream = response.getOutputStream();
            int len;
            byte[] b = new byte[1024];
            while (true) {
                len = inputStream.read(b);
                if (len == -1) break;
                servletOutputStream.write(b, 0, len);
            }
            // 释放资源

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            servletOutputStream.close();
            inputStream.close();
            httpURLConnection.disconnect();
        }

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


