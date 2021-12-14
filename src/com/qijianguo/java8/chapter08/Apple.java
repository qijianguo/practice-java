package com.qijianguo.java8.chapter08;

import java.util.*;
import java.util.stream.Collectors;

public class Apple {

    private int weight;

    public Apple(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public static void main(String[] args) {
        Apple a1 = new Apple(10);
        Apple a2 = new Apple(20);
        Apple a3 = new Apple(5);
        Apple a4 = new Apple(10);

        List<Apple> list = Arrays.asList(a1, a2, a3, a4);
        //list.sort((app1, app2) -> app1.getWeight() > app2.getWeight() ? 1 : -1);
        //list.sort(Apple::compareTo);
        list.sort(Comparator.comparing(Apple::getWeight));
        for (Apple apple : list) {
            System.out.println(apple);
        }
        System.out.println("=======================");
        // 最大
        Optional<Apple> max = list.stream().max((a, b) -> a.getWeight() > b.getWeight() ? 1 : -1);
        Optional<Apple> max1 = list.stream().max(Comparator.comparing(Apple::getWeight));
        System.out.println("max= " + max);
        System.out.println("max1= " + max1);
        System.out.println("=======================");
        // 总重
        Integer sum1 = list.stream().map(Apple::getWeight).reduce(0, (a, b) -> a + b);
        Integer sum2 = list.stream().collect(Collectors.summingInt(Apple::getWeight));
        System.out.println("sum1:" + sum1);
        System.out.println("sum2:" + sum2);
        System.out.println("=======================");
        // 统计
        IntSummaryStatistics stat = list.stream().collect(Collectors.summarizingInt(Apple::getWeight));
        System.out.println("min=" + stat.getMin() + " avg=" + stat.getAverage() + " max=" + stat.getMax());
        System.out.println("=======================");
    }


//    @Override
    public int compareTo(Apple o) {
        return o.getWeight() > this.weight ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                '}';
    }
}
