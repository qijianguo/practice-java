package com.qijianguo.java8.chapter06;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumberTest {

    public static void main(String[] args) {
        primeNumberCollectorTest();
        primeNumberPartitionedTest();
    }



    private static void primeNumberPartitionedTest() {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Map<Boolean, List<Integer>> collect = IntStream.range(0, 1000000)
                    .boxed().collect(Collectors.partitioningBy(j -> PrimeNumberPartitioned.isPrimeV2(j)));
            long duration = (System.nanoTime() - start) / 1000000 ;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");      // 57
    }

    private static void primeNumberCollectorTest() {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Map<Boolean, List<Integer>> collect = IntStream.range(0, 1000000).boxed().collect(new PrimeNumberCollector());
            long duration = (System.nanoTime() - start) / 1000000 ;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");      // 57
    }
}
