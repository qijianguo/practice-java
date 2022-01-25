package com.qijianguo.java8.chapter14;

import java.util.function.DoubleUnaryOperator;

/**
 * @author qijianguo
 */
public class Converter {

    DoubleUnaryOperator converter(double f, double b) {
        Integer i = 1;
        return (double x) -> x * f + b;
    }
}
