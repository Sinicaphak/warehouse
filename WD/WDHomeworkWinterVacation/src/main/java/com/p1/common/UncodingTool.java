package com.p1.common;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * 使用@RequestParam传值时会返回ISO8859-1编码的字符，
 * 用该类将字符改为UTF-8
 * */
public class UncodingTool {

    public static String encodingStr(String str) {
        try {
            return new String(str.getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
