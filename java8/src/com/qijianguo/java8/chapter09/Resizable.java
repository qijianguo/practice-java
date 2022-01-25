package com.qijianguo.java8.chapter09;

/**
 * @author qijianguo
 */
public interface Resizable {

    void setHeight(Integer height);

    void setWidth(Integer width);

    Integer getHeight();

    Integer getWidth();

    void setAbsoluteSize(Integer height, Integer width);


}
