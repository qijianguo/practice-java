package com.qijianguo.java8.other;

import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * 自定义延迟绑定的 Spliterator
 * @author qijianguo
 */
public class BlockingQueueSpliterator<T> implements Spliterator<T> {

    private final BlockingQueue<T> q;

    public BlockingQueueSpliterator(BlockingQueue<T> q) {
        this.q = q;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        T t;
        while (true) {
            try {
                t = q.take();
                break;
            } catch (InterruptedException ignore) {}
        }
        if (t != ForkingStreamCustomer.END_OF_STREAM) {
            action.accept(t);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
