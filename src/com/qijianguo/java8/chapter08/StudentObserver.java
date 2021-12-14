package com.qijianguo.java8.chapter08;

public class StudentObserver implements Observer {
    @Override
    public void notify(String message) {
        System.out.println("Student received message : " + message);
    }
}
