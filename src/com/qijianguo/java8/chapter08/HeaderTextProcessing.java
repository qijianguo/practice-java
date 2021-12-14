package com.qijianguo.java8.chapter08;

public class HeaderTextProcessing extends ProcessingObject<String> {

    public HeaderTextProcessing(ProcessingObject processingObject) {
        super(processingObject);
    }

    @Override
    protected String handleWork(String input) {
        return "Java 8: " + input;
    }
}
