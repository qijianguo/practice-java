package com.qijianguo.java8.chapter07;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 自定义可分迭代器：单词计数器
 * @author qijianguo
 */
public class WordCounterSpliterator implements Spliterator<Character> {

    /** 内容 */
    private final String string;
    /** 当前字符索引 */
    private int currentCharIdx = 0;
    /** 不可拆分的下限 */
    private final int MIN_LEN = 10_0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    /**
     * 类似普通的itrator，顺序的使用Spliterator中的元素
     * @param action
     * @return true 如果有元素则返回true，否则返回false
     */
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentCharIdx++));
        return currentCharIdx < string.length();
    }

    /**
     * 把一些元素划分给第二个Spliterator并返回
     * 目的是可以并行处理
     * @return 如果不能再分割，则返回 NULL
     */修改项目结构
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentCharIdx;
        // 设置不可拆分的下限
        if (MIN_LEN >= currentSize) {
            return null;
        }
        for (int splitPos = currentSize / 2 + currentCharIdx; splitPos < string.length(); splitPos ++) {
            if (!Character.isWhitespace(string.charAt(splitPos))) {
                WordCounterSpliterator spliterator = new WordCounterSpliterator(string.substring(currentCharIdx, splitPos));
                currentCharIdx = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    /**
     * 估算还剩下多少元素要遍历
     * @return 大概的剩余的元素个数
     */
    @Override
    public long estimateSize() {
        return string.length() - currentCharIdx;
    }

    /**
     * 特性
     * https://weread.qq.com/web/reader/faf32510718ff5e5fafd211
     * @return
     */
    @Override
    public int characteristics() {
        /*
            ORDERED： 字符串中Character是顺序的
            SIZED：estimatedSize返回值是精确的
            SUBSIZED：trySplit 方法创建的也是精确大小
            NONNULL：字符串中没有NULL的Character
            IMMUTABLE：在解析过程中不能再添加Character，因为字符串不可变的
         */
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
