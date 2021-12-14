package com.qijianguo.java8.chapter08;

public interface Subject {

    void registerObserver(Observer observer);

    void notifyObservers(String message);

    void removeObserver(Observer observer);

}
