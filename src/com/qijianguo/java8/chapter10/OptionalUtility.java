package com.qijianguo.java8.chapter10;

import java.util.Optional;

public class OptionalUtility {

    public static int StringToInt(Object num) {
        int i = 0;
        try {
            i = Integer.parseInt(String.valueOf(num));
        } catch (NumberFormatException ignore) {}
        return i;
    }
}
