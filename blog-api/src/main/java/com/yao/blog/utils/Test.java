package com.yao.blog.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @version 1.0
 * @Description TODO
 * @Author 17
 * @date 2022/8/19 21:07
 **/
public class Test {
    private static final String slat = "115080";

    public static void main(String[] args) {
        String accout = "admin";
        String password = "admin";

        password = DigestUtils.md5Hex(password+slat);

        System.out.println(password);

    }
}
