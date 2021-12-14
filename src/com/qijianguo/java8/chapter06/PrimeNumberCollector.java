package com.qijianguo.java8.chapter06;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * @author qijianguo
 */
public class PrimeNumberCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(2){{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (map, candidate) -> {
            map.get(isPrimeV2(map.get(true), candidate)).add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        // throw new UnsupportedOperationException();
        return (m1, m2) -> {
            m1.get(true).addAll(m2.get(true));
            m1.get(false).addAll(m2.get(false));
            return m1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    private static boolean isPrimeV2(List<Integer> list, int candidate) {
        // return list.stream().range(2, (int) Math.sqrt(candidate)).noneMatch(i -> candidate % i == 0);
        return takeWhile(list, i -> i < Math.sqrt(candidate)).stream().noneMatch(p -> p == 0 || candidate % p == 0);
    }

    private static List<Integer> takeWhile(List<Integer> list, Predicate<Integer> p) {
        int i = 0;
        for (Integer item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i ++;
        }
        return list;
    }

    public static void main(String[] args) {
        Map<Boolean, List<Integer>> collect = IntStream.range(0, 100).boxed().collect(new PrimeNumberCollector());
        System.out.println(collect);
    }
}
