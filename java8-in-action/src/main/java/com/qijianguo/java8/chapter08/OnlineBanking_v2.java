package com.qijianguo.java8.chapter08;

import java.util.function.Consumer;

public class OnlineBanking_v2 {

    public void processCustomer(int customerId, Consumer<Customer> notice) {
        Customer customer = getById(customerId);
        notice.accept(customer);
    }

    /**
     * 模拟DB
     * @param customerId
     * @return
     */
    private Customer getById(int customerId) {
        return new Customer(customerId);
    }

}
