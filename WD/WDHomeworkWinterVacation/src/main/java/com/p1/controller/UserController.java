package com.p1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageInfo;
import com.p1.common.Result;
import com.p1.common.TokenUnit;
import com.p1.pojo.DownloadHistory;
import com.p1.pojo.Song;
import com.p1.pojo.User;
import com.p1.service.inter.DownloadHistoryService;
import com.p1.service.inter.SongService;
import com.p1.service.inter.UserService;
import org.springframework.aot.hint.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final int DELECT_BY_ID= 0;
    public static final int DELECT_ALL= 1;

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("downloadHistoryServiceImpl")
    DownloadHistoryService downloadHistoryService;

    @Autowired
    @Qualifier("songServiceImpl")
    SongService songService;

    ObjectMapper objectMapper=new ObjectMapper();

    TokenUnit tokenUnit= TokenUnit.getTokenUnit();

    @PostMapping
    public String register(@RequestBody String json) throws JsonProcessingException {
        Result result;
        JsonNode jsonNode=objectMapper.readTree(json);
        String username=jsonNode.get("username").asText();
        String password=jsonNode.get("password").asText();
        String checkPassword=jsonNode.get("checkPassword").asText();

        if (userService.registerCheck(username, password, checkPassword)){

            User user=new User();
            user.setUserName(username);
            user.setUserPassword(password);
            user.setUserId(userService.selectUserByName(username).getUserId());     //获取新用户id并返回

            result=Result.gainSuccess();
            result.putIdAndName(user);

            return Result.outputJson(result);
        }else {
            return Result.outputJson(Result.gainNotFound());
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody String json) throws JsonProcessingException {
        JsonNode jsonNode=objectMapper.readTree(json);
        String username=jsonNode.get("username").asText();
        String password=jsonNode.get("password").asText();

        if (userService.loginCheck(username,password)) {
            String token=tokenUnit.createToken(userService.selectUserByName(username));     //签发token

            Result result=Result.gainSuccess();
            result.putIdAndName(userService.selectUserByName(username));
            result.putObject("token",token);

            return Result.outputJson(result);
        } else {
            return Result.outputJson(Result.gainNotFound());
        }
    }

    @DeleteMapping("/history")
    public String deleteDownloadHistory(@RequestBody String json) throws JsonProcessingException {
        JsonNode jsonNode=objectMapper.readTree(json);
        int type=jsonNode.get("type").asInt();
        int id=jsonNode.get("id").asInt();
        JsonNode jsonNodeList=jsonNode.get("list");
        List<Integer> list=new ArrayList<>();
        //怎么直接提取json中的数组？？？
        //将json中数组数据遍历填入本地的list
        for (JsonNode jsonNodeId:jsonNodeList){
            String sid=jsonNodeId.toString();
            list.add(Integer.parseInt(sid));
        }

        if (type==DELECT_BY_ID){
            downloadHistoryService.deleteDownloadHistoryByDownloadHistoryId(id);
            return Result.outputJson(Result.gainSuccess());
        }else if (type==DELECT_ALL){
            downloadHistoryService.deleteDownloadHistoryByList(list);
            return Result.outputJson(Result.gainSuccess());
        }else {
            return Result.outputJson(Result.gainNotFound());
        }
    }

    @GetMapping("/history")
    public String getDownloadHistory(@PathVariable int page,@RequestHeader("Authorization") String token) throws JsonProcessingException {
       int userId=TokenUnit.gainUserIdByToken(token);

        Pageable pageable= PageRequest.of(page, DownloadHistoryService.PAGE_SIZE);
        PageInfo<Song> songPage=downloadHistoryService.gainSongByPage(userId, pageable.getPageNumber());
        List<Song> list=songPage.getList();

        Result result=Result.gainSuccess();
        result.putObject("list", list);
        result.putObject("count", songPage.getPageSize());

        return Result.outputJson(result);
    }

    @PutMapping("/lc")
    public String updateDownloadHistoryFav(@RequestBody String json) throws JsonProcessingException {
        JsonNode jsonNode=objectMapper.readTree(json);
        String fav=jsonNode.get("fav").asText();
        String id=jsonNode.get("id").asText();

        int downloadHistoryId=Integer.parseInt(id);
        int isFav=Integer.parseInt(fav);

        int songRid=downloadHistoryService.selectDownloadHistoryById(downloadHistoryId).getRid();
        Song song=songService.selectSongByRid(songRid);
        Result result;

        if (isFav== DownloadHistory.NO_FAV){
            downloadHistoryService.updateFav(isFav, downloadHistoryId);
            result=Result.gainSuccess();
            result.putSong(song);
        } else if (isFav==DownloadHistory.IS_FAV) {
            downloadHistoryService.updateFav(isFav, downloadHistoryId);
            result=Result.gainSuccess();
            result.putSong(song);
        }else {
            result=Result.gainNotFound();
        }
        return Result.outputJson(result);
    }
}
