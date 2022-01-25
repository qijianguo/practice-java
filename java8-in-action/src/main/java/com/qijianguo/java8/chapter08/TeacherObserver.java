package com.qijianguo.java8.chapter08;

public class TeacherObserver implements Observer {
    @Override
    public void notify(String message) {
        System.out.println("Teacher received message : " + message);
    }
}
