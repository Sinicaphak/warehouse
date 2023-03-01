package com.p1.exception.token;


import com.p1.exception.BaseException;

/**
 * token不存在
 * */
public class TokenNotFound extends BaseException {

    @Override
    public String toString() {
        return this.getClass().getName()+"：token不存在";
    }
}
