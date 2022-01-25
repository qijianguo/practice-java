package com.qijianguo.java8.other;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * @author qijianguo
 */
public class ForkingStreamCustomer<T> implements Results, Consumer<T> {

    public static final Object END_OF_STREAM = new Object();

    private final List<BlockingQueue<T>> queues;

    private final Map<Object, Future<?>> actions;

    public ForkingStreamCustomer(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {
        this.queues = queues;
        this.actions = actions;
    }

    /**
     * 将流中遍历的元素添加到所有的队列中
     * @param t
     */
    @Override
    public void accept(T t) {
        queues.forEach(q -> q.add(t));
    }

    /**
     * 将最后一个元素添加到队列中，表示该流已经结束
     */
    public void finish() {
        accept((T)END_OF_STREAM);
    }

    /**
     * 等待Future完成相关的计算，返回由特定键标识的处理结果
     * @param key
     * @param <R>
     * @return
     */
    @Override
    public <R> R get(Object key) {
        try {
            return ((Future<R>)actions.get(key)).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
