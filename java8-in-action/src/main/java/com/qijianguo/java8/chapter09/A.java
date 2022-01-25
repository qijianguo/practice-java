package com.qijianguo.java8.chapter09;

public interface A {

    default void print() {
        System.out.println("print A.");
    }
}
