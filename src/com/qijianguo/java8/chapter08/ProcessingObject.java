package com.qijianguo.java8.chapter08;

public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> processingObject;

    public ProcessingObject(ProcessingObject<T> processingObject) {
        this.processingObject = processingObject;
    }

    public T handle(T input) {
        T t = handleWork(input);
        if (processingObject != null) {
            return processingObject.handle(t);
        }
        return t;
    }

    abstract protected T handleWork(T input);

}
