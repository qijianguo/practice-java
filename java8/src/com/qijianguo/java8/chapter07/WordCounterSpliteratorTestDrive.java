package com.qijianguo.java8.chapter07;

import java.util.Spliterator;

import java.util.stream.IntStream;
importava.util.stream.StreamSupport;

/**
 * @author qijianguo
 */
public class WordCounterSpliteratorTestDrive {

    private static final String CONTENT = "         Alice           is      a docker         ";

    public static void main(String[] args) {
        // int i = countWordsIteratively(CONTENT);
        // int i = countWordsIteratively2(CONTENT);
        // countWordsIteratively3(IntStream.range(0, CONTENT.length()).mapToObj(CONTENT::charAt));
        countWordsIteratively4(CONTENT);
    }

    /**
     * 自定义 Spliterator 并行执行
     * @param s
     * @return
     */
    private static int countWordsIteratively4(String s) {
        Spliterator spliterator = new WordCounterSpliterator(s);
        Stream stream = StreamSupport.stream(spliterator, true);
        return countWordsIteratively3(stream);
    }

    /**
     * Stream流方式顺序处理（不支持并行处理）
     * @param stream
     * @return
     */
    private static int countWordsIteratively3(Stream<Character> stream) {
        int counter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine).getCounter();
        // parallel not support!
        // int counter = stream.parallel().reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine).getCounter();
        System.out.println(counter);
        return counter;
        //new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine
    }

    private static int countWordsIteratively2(String s) {
        WordCounter result = new WordCounter(0, true);
        for (char aChar : s.toCharArray()) {
            result = result.accumulate(aChar);
        }
        System.out.println(result.getCounter());
        return result.getCounter();
    }

    /**
     * 原始方式处理
     * @param s
     * @return
     */
    private static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastWhitespace = true;
        for (char aChar : s.toCharArray()) {
            if (Character.isWhitespace(aChar)) {
                lastWhitespace = true;
                System.out.println();
            } else {
                System.out.print(aChar);
                if (lastWhitespace) {
                    counter ++;
                    lastWhitespace = false;
                }
            }
        }
        System.out.println(counter);
        return counter;
    }

}
