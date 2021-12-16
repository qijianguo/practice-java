package com.qijianguo.java8.chapter10;

import java.util.Optional;

public class Car {

    private Optional<Insurance> optInsurance;

    public Optional<Insurance> getOptInsurance() {
        return optInsurance;
    }

    public void setOptInsurance(Optional<Insurance> optInsurance) {
        this.optInsurance = optInsurance;
    }
}
