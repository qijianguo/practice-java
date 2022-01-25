package com.qijianguo.java8.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamForker<T> {

    private final Stream<T> stream;

    private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap<>();

    public StreamForker(Stream<T> stream) {
        this.stream = stream;
    }

    /**
     * 将Key/Function放到数据结构中
     * @param key
     * @param f
     * @return
     */
    public StreamForker fork(Object key, Function<Stream<T>, ?> f) {
        forks.put(key, f);
        return this;
    }

    public Results getResults() {
        ForkingStreamCustomer<T> consumer = build();
        try {
            stream.sequential().forEach(consumer);
        } finally {
            consumer.finish();
        }

        return null;
    }

    private ForkingStreamCustomer<T> build() {

        List<BlockingQueue<T>> queues = new ArrayList<>();

        Map<Object, Future<?>> actions = forks.entrySet().stream().reduce(
                new HashMap<Object, Future<?>>(forks.size()),
                (map, e) -> {
                    map.put(e.getKey(), getOperationResult(queues, e.getValue()));
                    return map;
                },
                (m1, m2) -> {
                    m1.putAll(m2);
                    return m1;
                }
        );
        return new ForkingStreamCustomer<>(queues, actions);
    }

    /**
     *
     * @param queues
     * @param f
     * @return
     */
    private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>,?> f) {
        BlockingQueue<T> queue = new LinkedBlockingDeque<>();
        queues.add(queue);
        BlockingQueueSpliterator<T> bqs = new BlockingQueueSpliterator<>(queue);
        Stream<T> source = StreamSupport.stream(bqs, false);
        return CompletableFuture.supplyAsync(() -> f.apply(source));
    }
}
