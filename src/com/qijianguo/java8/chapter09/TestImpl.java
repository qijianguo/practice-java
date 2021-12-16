package com.qijianguo.java8.chapter09;

public class TestImpl implements ITest {

    @Override
    public void test01() {

    }

    @Override
    public void test02() {

    }

    public static void main(String[] args) {
        ITest test = new TestImpl();
        System.out.println(test.newMethod());
    }
}
