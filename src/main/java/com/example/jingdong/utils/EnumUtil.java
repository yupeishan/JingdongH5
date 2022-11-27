package com.example.jingdong.utils;

import com.example.jingdong.enums.BaseEnum;

import java.util.HashMap;
import java.util.Map;

public class EnumUtil {

    /**
     * 传入一个枚举类 转换为map 用于前端展示
     * @param enumClass 需要转换的枚举类
     * @return          Map<Integer, String>
     */
    public static Map<Integer, String> enumToMap(Class<? extends BaseEnum<Object>> enumClass) {
        Map<Integer, String> map = new HashMap<>();

        if(!enumClass.isEnum()) {
            throw new UnsupportedOperationException("参数不合法：非枚举类，不支持转换，请检查程序是否有误！");
        }

        // 通过class.getEnumConstants();获取所有的枚举字段和值
        BaseEnum<Object>[] codeEnums = enumClass.getEnumConstants();
        for (BaseEnum<Object> codeEnum : codeEnums) {
            map.put(codeEnum.getCode(), codeEnum.getMessage());
        }
        return map;

    }



}
