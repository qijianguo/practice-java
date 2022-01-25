package com.qijianguo.java8.chapter06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    /**
     * 建立结果容器
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        //return () -> new ArrayList<>();
        return ArrayList::new;
    }

    /**
     * 将元素添加到容器
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        // return (list, item) -> list.add(item);
        return List::add;
    }

    /**
     * 容器内容最终转换
     * @return
     */
    @Override
    public Function finisher() {
        // 恒等函数
        return Function.identity();
    }

    /**
     * 合并两个结果容器
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT, Characteristics.IDENTITY_FINISH));
    }

    public static void main(String[] args) {
        List<Integer> collect = v1();
        //List<Integer> collect = v2();
        System.out.println(collect);
    }

    private static List<Integer> v1() {
        // 自定义
        return IntStream.range(0, 100).boxed().collect(new ToListCollector<>());
    }

    private static List<Integer> v2() {
        // Stream#collect(Supplier,Accumulator, Combiner)
        return IntStream.range(0, 100).boxed()
                .collect(
                        ArrayList::new, // 供应源
                        List::add, // 累加器
                        List::addAll); // 组合器
    }
}
