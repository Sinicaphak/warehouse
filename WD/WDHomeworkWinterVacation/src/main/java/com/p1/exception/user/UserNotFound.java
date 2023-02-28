package com.p1.exception.user;

import com.p1.exception.BaseException;
/**
 * 用户不存在
 * */
public class UserNotFound extends BaseException {
    private String username;

    public UserNotFound(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"：找不到用户{"+username+"}";
    }
}
