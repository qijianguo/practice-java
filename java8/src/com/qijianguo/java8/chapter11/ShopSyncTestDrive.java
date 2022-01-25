package com.qijianguo.java8.chapter11;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ShopSyncTestDrive {

    private List<Shop> shops = Arrays.asList(
            new Shop("SA"),
            new Shop("SB"),
            new Shop("SC"),
            new Shop("SD"),
            new Shop("SE"),
            new Shop("SF"),
            new Shop("SG"),
            new Shop("SH")
    );

    public Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });


    public static void main(String[] args) {
        ShopSyncTestDrive test = new ShopSyncTestDrive();

//        test.stream(); // duration : 8086ms
//        test.parallelStream(); // duration : 1006ms
//        test.completableFuture(); // duration : 1010ms
//        test.completableFutureWithExecutor(); // duration : 1004ms
        test.findPrices();
    }

    public List<String> findPrices() {
        long begin = begin();
        // duration : 8080ms
        List<String> collect = shops.stream()
                .map(Shop::getPriceStr)
                .peek(System.out::println)
                .map(Quote::parse)
                .peek(System.out::println)
                .map(Discount::applyDiscount)
                .peek(System.out::println)
                .collect(Collectors.toList());

        /*List<CompletableFuture<String>> priceFuture =  shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceStr(), executor))
                .map(future -> future.thenAccept(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                    CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());*/

        duration(begin);
        return collect;
    }

    private void cpu() {
        // CPU的核数
        int nCpu = Runtime.getRuntime().availableProcessors();
        // 期望的CPU利用率
        float uCpu = 0.8f;
        // W/C是等待时间与计算时间的比率
        float wc = 100f;

        int nThreadNums = (int) (nCpu * uCpu * (1 + wc));

    }

    private void completableFutureWithExecutor() {
        long begin = begin();
        //OptionalDouble min = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice())).mapToDouble(CompletableFuture::getNumberOfDependents).min();
        List<CompletableFuture<Double>> collect = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(), executor)).collect(Collectors.toList());
        DoubleSummaryStatistics doubleSummaryStatistics = collect.stream().map(CompletableFuture::join).mapToDouble(aD -> aD.doubleValue()).summaryStatistics();
        System.out.println(doubleSummaryStatistics);
        duration(begin);
    }


    private void completableFuture() {
        long begin = begin();
        //OptionalDouble min = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice())).mapToDouble(CompletableFuture::getNumberOfDependents).min();
        List<CompletableFuture<Double>> collect = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice())).collect(Collectors.toList());
        DoubleSummaryStatistics doubleSummaryStatistics = collect.stream().map(CompletableFuture::join).mapToDouble(aD -> aD.doubleValue()).summaryStatistics();
        System.out.println(doubleSummaryStatistics);
        duration(begin);
    }

    private void parallelStream() {
        long begin = begin();
        DoubleSummaryStatistics doubleSummaryStatistics = shops.parallelStream().mapToDouble(Shop::getPrice).summaryStatistics();
        System.out.println(doubleSummaryStatistics);
        duration(begin);
    }

    private void stream() {
        long begin = begin();
        DoubleSummaryStatistics doubleSummaryStatistics =  shops.stream().mapToDouble(Shop::getPrice).summaryStatistics();
        System.out.println(doubleSummaryStatistics);
        duration(begin);
    }

    private static long begin() {
        return System.currentTimeMillis();
    }

    public static void duration(long start) {
        long end = System.currentTimeMillis();

        System.out.println("duration : " + (end - start) + "ms");
    }

}
