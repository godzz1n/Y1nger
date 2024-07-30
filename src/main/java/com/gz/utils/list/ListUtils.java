package com.gz.utils.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gao.zhen
 * @date 2024/7/30 15:13
 */
public class ListUtils {

    /**
     * 根据访问列表和给定的数字，将列表分割成多个子列表。
     * 每个子列表的长度大约相等，取决于给定的数字。
     * 此方法主要用于将一个大的列表分解成小的、更易处理的列表。
     *
     * @param visitList 原始的访问列表，包含需要分割的字符串元素。
     * @param num 指定的分割数，用于确定每个子列表的大致长度。eg:3
     * @return 返回一个包含多个子列表的列表。每个子列表都尽量包含相同数量的元素。(不是整除最后一个数量不够)
     */
    private static List<List<String>> getLists(ArrayList<String> visitList, int num) {
        return IntStream.range(0, visitList.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> i / num))
                .values()
                .stream()
                .map(indices -> indices.stream().map(visitList::get).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

   /**
    * 获取山东省各地市的列表。
    *
    * 该方法通过将字符串按照逗号分隔，转换为字符串数组，进而封装为列表形式返回。
    * 这样做的目的是为了方便对山东省各地市的名称进行集合管理，例如遍历展示或进一步处理。
    *
    * @return 返回一个包含山东省各地市名称的字符串列表。
    */
   private static List<String> getSDList() {
        // 定义字符串包含山东省所有地市的名称，各名称之间用逗号分隔。
        String mapStr = "济南,青岛,淄博,枣庄,东营,烟台,潍坊,威海,济宁,德州,聊城,滨州,菏泽,泰安,临沂,日照";
        String[] split = mapStr.split(",");
        return new ArrayList<>(Arrays.asList(split));
    }

    /**
     * 将给定对象转换为指定类型的列表。
     * 该方法主要用于处理类型转换问题，当需要将一个List中的元素全部转换为特定类型时，可以使用此方法。
     *
     * @param obj 需要转换的对象，预期是一个List类型的对象。
     * @param clazz 指定的类型，用于将列表中的元素转换为此类型。
     * @param <T> 泛型参数，表示转换后的类型。
     * @return 转换后的列表，如果输入对象不是List类型，则返回null。
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }


}
