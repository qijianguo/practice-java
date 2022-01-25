package com.qijianguo.java8.chapter11;

import java.util.Random;
import java.util.concurrent.*;

public class Shop {

    private String name;

    private Double price;

    public Shop() {
    }

    public Shop(String name) {
        this.name = name;
    }

    private Random random = new Random();

    public double getPrice() {
        return calculatePrice(name);
    }

    public String getPriceStr() {
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%s:%s", name, price = calculatePrice(name), code);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            delayExp();
            future.complete(calculatePrice(product));
        }).start();
        return future;
    }


    public Future<Double> getPriceAsync2(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                delayExp();
                double v = calculatePrice(product);
                future.complete(v);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();
        return future;
    }

    public Future<Double> getPriceAsync3(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }



    public double calculatePrice(String product) {
        delay();
        return price = random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void delayExp() {
        try {
            Thread.sleep(2000L);
            int i = 1/0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Shop shop = new Shop();
        Future<Double> async = shop.getPriceAsync("apple");
        Future<Double> async2 = shop.getPriceAsync2("apple");
        // doSthElse();
        try {
            Double aDouble = async.get(2, TimeUnit.SECONDS);
            async2.get(2, TimeUnit.SECONDS);
            System.out.println(aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


}
