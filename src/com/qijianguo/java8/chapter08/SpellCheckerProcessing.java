package com.qijianguo.java8.chapter08;

public class SpellCheckerProcessing extends ProcessingObject<String> {

    public SpellCheckerProcessing(ProcessingObject processingObject) {
        super(processingObject);
    }

    @Override
    protected String handleWork(String input) {
        return input.replaceAll("lamda", "lambda");
    }
}
