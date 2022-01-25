package com.qijianguo.java8.chapter08;

public class ThisSuperTestDrive {

    String message = "Outer";

    public static void main(String[] args) {
        ThisSuperTestDrive thisSuperTestDrive = new ThisSuperTestDrive();
        thisSuperTestDrive.innerClass();
        thisSuperTestDrive.lambda();
    }


    private void lambda() {
        ITest iTest = () -> {
            String inner = "Lambda";
            System.out.println("Lambda: " + this.message);
            return this.message;
        };
        iTest.print();
    }

    private void innerClass() {
        new ITest() {
            String message = "InnerClass";
            @Override
            public String print() {
                System.out.println("匿名类: " + this.message);
                return this.message;
            }
        }.print();

    }
}
