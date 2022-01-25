package com.qijianguo.java8.chapter08;

/**
 * Represents a supplier of results.
 *
 * @author qijianguo
 */
public interface TwoSupplier<T, K, N> {

    /**
     * Gets a result.
     * @param k
     * @param n
     * @return T
     */
    T get(K k, N n);
}
