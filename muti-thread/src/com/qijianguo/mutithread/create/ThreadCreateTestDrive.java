package com.qijianguo.mutithread.create;

import java.util.concurrent.*;

public class ThreadCreateTestDrive {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "running...");
        testCreateByThread();
        testCreateByRunnable();
        testCreateByAnonymous();
        testCreateByLambda();
        testCreateByCallableAndFutureTask();
    }

    private static void testCreateByThread() {
        Thread thread = new CreateByThread();
        thread.setName("NewThreadByThread");
        thread.start();
    }

    private static void testCreateByRunnable() {
        Thread thread = new Thread(new CreateByRunnable(), "NewThreadByRunnable");
        thread.start();
    }

    private static void testCreateByAnonymous() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " running...");
            }
        }, "NewThreadByAnonymous");
        thread.start();
    }

    private static void testCreateByLambda() {
        Thread thread = new Thread(() ->
                System.out.println(Thread.currentThread().getName() + " running...")
        , "NewThreadByLambda");
        thread.start();
    }

    private static void testCreateByCallableAndFutureTask() {
        RunnableFuture<String> futureTask = new FutureTask<>(new CreateByCallable());
        Thread thread = new Thread(futureTask, "NewThreadByCallableAndFutureTask");
        thread.start();
        try {
            String o = null;
            while (!futureTask.isDone()) {
                o = futureTask.get(3, TimeUnit.SECONDS);
            }
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


}
