package com.qijianguo.java8.chapter09;

public interface B/* extends A*/{

    default void print() {
        System.out.println("print B.");
    }
}
