package com.p1.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.p1.pojo.User;


import java.util.Date;

/**
 * Token工具类
 * */
public class TokenUnit {

    /**
     * Token有效期
     * */
    private static final long TERM_OF_VALIDITY = 60*60*1000;

    /**
     * 全局唯一TokenUnit
     * */
    private static TokenUnit tokenUnit=new TokenUnit();

    /**
     * 私匙
     * */

    public static String PRIVATE_KEY="yi1yu4din2zhen2jian4ding4wei2fu1zhou2da4zhuan2";

    private TokenUnit(){}

    public static TokenUnit getTokenUnit(){
        if (tokenUnit!=null){
            tokenUnit =new TokenUnit();
        }
        return tokenUnit;
    }

    /**
     * 根据用户id构建token
     * */
    public String createToken(User user){
        Date expireAt =new Date(System.currentTimeMillis()+TERM_OF_VALIDITY);
        String token="";
        token= JWT.create().
                withClaim("userId", user.getUserId()).
                withExpiresAt(expireAt).
                sign(Algorithm.HMAC256(PRIVATE_KEY));
        return token;
    }

    /**
     * 识别token
     * */
    public static boolean verify(String token){
        try {
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(PRIVATE_KEY)).build();
            jwtVerifier.verify(token);
            return true;
        }catch (IllegalArgumentException | JWTVerificationException e){
            return false;
        }
    }

    /**
     * 从token中获取用户id
     * */
    public static int gainUserIdByToken(String token){
        if (verify(token)){
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(PRIVATE_KEY)).build();
            return jwtVerifier.verify(token).getClaim("userId").asInt();
        }
        return -1;
    }
}
