package com.qijianguo.java8.chapter10;

import java.util.Optional;

public class Person {

    private String username;

    private Optional<Car> optCar;

    public Optional<Car> getOptCar() {
        return optCar;
    }

    public void setOptCar(Optional<Car> optCar) {
        this.optCar = optCar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
