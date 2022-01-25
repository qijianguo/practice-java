package com.qijianguo.java8.chapter08;

public class Loan extends Product{

    private Integer id;

    private String name;

    public Loan(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "} ";
    }
}
