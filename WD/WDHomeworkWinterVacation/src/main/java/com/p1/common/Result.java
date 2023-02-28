package com.p1.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1.pojo.Song;
import com.p1.pojo.User;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result{

    public static final Map<String,Object> NULL_DATA =Map.of("NULL_DATA", "NULL_DATA") ;

    public static final int NULL_CODE=-1;

    private static final String NULL_MSG="NULL_MSG";

    private int code=NULL_CODE;

    private String message=NULL_MSG;
    private Map<String,Object> data= new HashMap<String, Object>();

    private static ObjectMapper objectMapper=new ObjectMapper();

    public Result(){};

    private Result(ResultCode resultCode) {
       code=resultCode.getCode();
       message=resultCode.getMessage();
    }

    /**
     * 向data中存放数据
     * */
    public void putObject(String name, Object key){
        data.put(name, key);
    }

    /**
     * 获取不同类型Result
     * */
    public static Result gainSuccess(){
        Result result=new Result(ResultCode.SUCCESS);
        return result;
    }
    public static Result gainNotFound(){
        Result result=new Result(ResultCode.NOT_FOUND);
        return result;
    }

    /**
     * 向data中存放用户信息
     * */
    public void putIdAndName(User user){
        data.put("id", user.getUserId()+"");
        data.put("username",user.getUserName());
    }

    /**
     * 向data中存放歌曲信息
     * */
    public void putSong(Song song){
        data.put("name", song.getSongName());
        data.put("artist", song.getArtist());
        data.put("album", song.getAlbum());
        data.put("duration", song.getDuration());
        data.put("rid", song.getRid());
    }

    /**
     * 解析json
     * */
    public static String outputJson(Result result) throws JsonProcessingException {
        if (result.getData().isEmpty()){
            result.setData(NULL_DATA);
        }
        return objectMapper.writeValueAsString(result);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        Result.objectMapper = objectMapper;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String,Object> getData(){
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
