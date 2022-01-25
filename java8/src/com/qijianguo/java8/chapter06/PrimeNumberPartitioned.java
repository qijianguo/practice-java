package com.qijianguo.java8.chapter06;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 将数字按质数和非质数分区
 *
 * @author qijianguo
 */
public class PrimeNumberPartitioned {

    public static void main(String[] args) {
        Map<Boolean, List<Integer>> collect = IntStream.range(0, 100)
                .boxed().collect(Collectors.partitioningBy(i -> isPrimeV2(i)));
        System.out.println(collect);

    }

    public static boolean isPrime(int candidate) {
        return !IntStream.range(2, candidate)
                .anyMatch(i -> candidate % i == 0);
    }

    public static boolean isPrimeV2(int candidate) {
        return IntStream.range(2, (int) Math.sqrt(candidate)).noneMatch(i -> candidate % i == 0);
    }



}
