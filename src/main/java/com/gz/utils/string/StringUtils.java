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
    /**
     * 合并参数为一个字符串。
     * 使用指定的分隔符将多个对象连接成一个字符串。此方法允许接收任意数量的参数。
     * 如果没有提供参数，则返回null。
     *
     * @param spliterStr 分隔符，用于分隔每个对象。注意，分隔符本身不会被添加到字符串的开始或结束。
     * @param objs 可变参数，要连接的对象。可以是任意类型的对象，它们将被转换为字符串形式。
     * @return 返回一个字符串，包含所有参数按分隔符连接的结果。如果参数objs为null，则返回null。
     */
    public static String concatParams(String spliterStr, Object... objs) {

        StringBuilder sb = new StringBuilder();
        if (objs != null) {
            for (int i = 0; i < objs.length; i++) {
                sb.append(objs[i]);
                if (i != objs.length - 1) {
                    sb.append(spliterStr);
                }
            }
            return sb.toString();
        }
        return null;
    }
}
