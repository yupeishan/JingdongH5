package com.example.jingdong.test;

import com.example.jingdong.utils.CalendarUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

public class CalendarTest {


    @Test
    public void test() {
        System.out.println("当前时间：" + new Date().toLocaleString());
        System.out.println("当天0点时间：" + CalendarUtil.getTodayStart().toLocaleString());
        System.out.println("当天24点时间：" + CalendarUtil.getTodayEnd().toLocaleString());
        System.out.println("本周一0点时间：" + CalendarUtil.getThisWeekStart().toLocaleString());
        System.out.println("本周日24点时间：" + CalendarUtil.getThisWeekEnd().toLocaleString());
        System.out.println("本月初0点时间：" + CalendarUtil.getThisMonthStart().toLocaleString());
        System.out.println("本月末24点时间：" + CalendarUtil.getThisMonthEnd().toLocaleString());

    }


}
