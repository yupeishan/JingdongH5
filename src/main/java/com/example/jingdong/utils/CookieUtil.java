package com.example.jingdong.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {

    public static void setCookie(String name, String value, int maxAge, HttpServletResponse response){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static Cookie getCookie(String name, HttpServletRequest request){
        Map<String,Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)){
           return cookieMap.get(name);
        }
        return null;
    }


    public static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();

        if (cookies != null){
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(),cookie);
            }
        }

        return cookieMap;
    }





}
