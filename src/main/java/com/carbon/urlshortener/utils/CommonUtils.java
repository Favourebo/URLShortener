package com.carbon.urlshortener.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtils {

    public static Long createId(){
        return Long.valueOf(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
    }


    public static String base62Encode(long value) {
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            sb.append(ConstantUtils.base62.charAt((int)(value % 62)));
            value /= 62;
        }
        while (sb.length() < 6) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }


    public static Long base62Decode(String shortUrl) {
        String base62Encoded = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
        long decode = 0;
        for(int i = 0; i < base62Encoded.length(); i++) {
            decode = decode * 62 + ConstantUtils.base62.indexOf("" + base62Encoded.charAt(i));
        }
        return decode;
    }
}
