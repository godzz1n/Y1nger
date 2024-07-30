package com.gz.utils.string;

import java.util.Random;

/**
 * @author gao.zhen
 * @date 2024/7/30 14:19
 */
public class StringUtils {
    /**
     * 随机生成一个四位字符串，其中第一位和第三位是大写字母，第二位和第四位是数字。
     *
     * @return 生成的字符串
     */
    public static String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // 生成大写字母
        sb.append((char) ('A' + random.nextInt(26)));
        // 生成数字
        sb.append(random.nextInt(10));
        // 生成大写字母
        sb.append((char) ('A' + random.nextInt(26)));
        // 生成数字
        sb.append(random.nextInt(10));

        return sb.toString();
    }
}
