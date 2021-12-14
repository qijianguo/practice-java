package com.qijianguo.java8.chapter07;

public class WordCounter {

    private final int counter;
    private final boolean lastWorkspace;

    public WordCounter(int counter, boolean lastWorkspace) {
        this.counter = counter;
        this.lastWorkspace = lastWorkspace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastWorkspace ? this : new WordCounter(counter, true);
        } else {
            return lastWorkspace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wc) {
        return new WordCounter(this.counter + wc.counter, wc.lastWorkspace);
    }

    public int getCounter() {
        return counter;
    }
}
