package com.qijianguo.java8.chapter09;

/**
 * @author qijianguo
 */
public interface B extends A {

    @Override
    default void print() {
        System.out.println("print B.");
    }
}
