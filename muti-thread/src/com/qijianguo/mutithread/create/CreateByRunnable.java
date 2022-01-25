package com.qijianguo.mutithread.create;

public class CreateByRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " running...");
    }
}
