package com.qijianguo.java8.chapter08;

public class ProductFactory {

    public static Product create(String type) {
        switch (type) {
            case "loan": return new Loan(2, "123213");
            case "stock": return new Stock();
            default:
                throw new IllegalArgumentException();
        }
    }
}
