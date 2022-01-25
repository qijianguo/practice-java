package com.qijianguo.java8.chapter08;

import java.util.ArrayList;
import java.util.List;

public class SchoolSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("observer is null!");
        }
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        observers.forEach(observer -> observer.notify(message));
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("observer is null!");
        }
        observers.remove(observer);
    }
}
