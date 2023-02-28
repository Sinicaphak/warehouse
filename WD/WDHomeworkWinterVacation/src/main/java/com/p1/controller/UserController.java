package com.p1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.p1.common.Result;
import com.p1.common.TokenUnit;
import com.p1.pojo.DownloadHistory;
import com.p1.pojo.Song;
import com.p1.pojo.User;
import com.p1.service.inter.DownloadHistoryService;
import com.p1.service.inter.SongService;
import com.p1.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

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
    public String deleteDownloadHistory(@RequestBody String json,@RequestHeader("Authorization") String token)
                                        throws JsonProcessingException {
        int userId = TokenUnit.gainUserIdByToken(token);
        JsonNode jsonNode=objectMapper.readTree(json);
        String type=jsonNode.get("type").asText();
        String id=jsonNode.get("id").asText();

        if (type.equals(DownloadHistory.NO_FAV+"")){
            downloadHistoryService.deleteDownloadHistoryByDownloadHistoryId(Integer.parseInt(id));
            return Result.outputJson(Result.gainSuccess());
        }else if (type.equals(DownloadHistory.IS_FAV+"")){
            downloadHistoryService.deleteAllDownloadHistory(userId);
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
