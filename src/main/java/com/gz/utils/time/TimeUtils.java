package com.gz.utils.time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @author gao.zhen
 * @date 2024/7/30 14:19
 */
public class TimeUtils {


    /**
     * 获取当前日期和时间的字符串表示
     * 该方法用于格式化当前系统时间，并打印出易于阅读的日期和时间格式。
     * 使用SimpleDateFormat类来定义日期时间的格式化方式。
     *
     * @see SimpleDateFormat 用于解析和格式化日期的类
     * @see Date 表示特定瞬间的时间点的类
     */
    public void getTime(){
        // 创建一个SimpleDateFormat实例，用于将日期时间格式化为字符串
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 输出格式化后的当前日期时间
        System.out.println("时间~~~~~~：" + df.format(new Date()));
    }

    /**
     * 获取从当前日期开始指定总数的月份列表，按照指定的格式倒序排列。
     *
     * @param total 想要获取的月份总数，正数表示从当前开始向前的月份，负数表示从当前向后的月份。
     * @param sdf   用于格式化日期的SimpleDateFormat对象，例如"yyyy.MM.dd"。
     * @return 返回一个包含指定总数的月份字符串列表，按照指定格式格式化，列表倒序排列。
     */
    private static List<String> getMonList(Integer total, SimpleDateFormat sdf) {
        List<String> dateList = new ArrayList<>();
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        for (int i = total - 1; i >= 0; i--) {
            Date date = DateUtils.addMonths(new Date(), -i);
            String formatDate = sdf.format(date);
            dateList.add(formatDate);
        }
        return dateList;
    }

    /**
     * 根据指定总数获取一系列日期字符串
     * 该方法主要用于生成一个包含从当前日期开始向前推算的指定数量天数的日期列表
     *
     * @param total 总数，表示需要生成的天数
     * @param sdf   日期格式化对象，用于指定日期字符串的格式
     * @return 返回一个包含日期字符串的列表
     */
    private static List<String> getDayList(Integer total, SimpleDateFormat sdf) {
        List<String> dateList = new ArrayList<>();
       // SimpleDateFormat sdf = new SimpleDateFormat("M.dd");
        for (int i = total; i > 0; i--) {
            Date date = DateUtils.addDays(new Date(), -i);
            String formatDate = sdf.format(date);
            dateList.add(formatDate);
        }
        return dateList;
    }

}
