package com.qijianguo.java8.chapter08;

public class Test {

    String message = "Outer";

    public static void main(String[] args) {
        Test test = new Test();
        test.test1();
        test.test2();
    }


    private void test2() {
        ITest iTest = () -> {
            String inner = "Inner";
            System.out.println("Lambda: " + this.message);
            return this.message;
        };
        iTest.print();
    }

    private void test1() {
        new ITest() {
            String message = "Inner";
            @Override
            public String print() {
                System.out.println("匿名类: " + this.message);
                return this.message;
            }
        }.print();

    }
}
