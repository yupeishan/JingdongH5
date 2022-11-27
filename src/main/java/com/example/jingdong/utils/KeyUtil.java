package com.example.jingdong.utils;

import java.util.Random;

public class KeyUtil {


    public static  synchronized String getUniqueKey(){
        Random random = new Random();
        int number = random.nextInt(900000)+100000;
        return String.valueOf(System.currentTimeMillis())+ number;
    }



}
