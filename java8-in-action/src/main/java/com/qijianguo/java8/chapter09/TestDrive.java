package com.qijianguo.java8.chapter09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestDrive {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,31,2, 4, 4);
        Comparator reversed = Comparator.naturalOrder().reversed();
        list.sort(reversed);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
