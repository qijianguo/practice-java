package com.qijianguo.java8.chapter14;

import java.util.function.DoubleUnaryOperator;

public class ConverterTestDrive {

    public static void main(String[] args) {
        Converter converter = new Converter();
        DoubleUnaryOperator converter1 = converter.converter(1, 2);
        double v = converter1.applyAsDouble(3);
        System.out.println(v);

    }
}
