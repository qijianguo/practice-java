package com.qijianguo.java8.chapter11;

import java.util.concurrent.*;

public class FutureTestDrive {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Callable<? extends Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return calculate();
            }
        };
        Future<?> submit = executorService.submit(callable);

        doSomethingElse();

        System.out.println(submit.isDone());

        while (submit.isDone()) {
            System.out.println("---");
            print(submit.get());
        }
        Object result = submit.get();
        System.out.println("main:" + result);
//        executorService.shutdown();
    }

    private static void print(Object object) {
        System.out.println("print: " + object);
    }

    private static void doSomethingElse() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("do sth else....");
    }

    private static int calculate() throws InterruptedException {
        Thread.sleep(1000);
        return 10000;
    }
}
