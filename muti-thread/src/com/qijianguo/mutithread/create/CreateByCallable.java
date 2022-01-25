package com.qijianguo.mutithread.create;

import java.util.concurrent.Callable;

/**
 * @author qijianguo
 */
public class CreateByCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " running...");
        return "callback";
    }
}
