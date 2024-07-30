package com.gz.utils.list;

import java.util.ArrayList;
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
     * @param num 指定的分割数，用于确定每个子列表的大致长度。
     * @return 返回一个包含多个子列表的列表。每个子列表都尽量包含相同数量的元素。
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

}
