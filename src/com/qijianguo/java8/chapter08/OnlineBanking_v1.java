package com.qijianguo.java8.chapter08;

public abstract class OnlineBanking_v1 {

    public void processCustomer(int customerId) {
        Customer customer = getById(customerId);
        notice(customer);
    }

    /**
     * 通知用户（传统方式）
     * @param customer
     */
    public abstract void notice(Customer customer);


    /**
     * 模拟DB
     * @param customerId
     * @return
     */
    private Customer getById(int customerId) {
        return new Customer(customerId);
    }

}
