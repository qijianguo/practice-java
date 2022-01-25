package com.qijianguo.java8.chapter11;

public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20)
        ;

        private int code;

        Code(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static Code match(Integer code) {
            for (Code value : Code.values()) {
                if (value.getCode() == code) {
                    return value;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public static String applyDiscount(Quote quote) {
        return "ShopName: " + quote.getShopName() + " Code: " + quote.getDiscountCode().getCode() + " Price:" + quote.getPrice();
    }

    private static double apply(double price, Code code) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return price * (100 - code.getCode()) / 100;
    }
}
