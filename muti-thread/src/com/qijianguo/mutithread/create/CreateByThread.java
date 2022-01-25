package com.qijianguo.mutithread.create;

public class CreateByThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " running...");
    }
}
