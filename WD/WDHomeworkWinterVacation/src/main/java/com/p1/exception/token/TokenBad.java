package com.p1.exception.token;

import com.p1.exception.BaseException;

/**
 * token异常
 * */
public class TokenBad extends BaseException {

    private String token;

    public TokenBad(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"：token过期或异常，token如下：{"+token+"}";
    }

}
