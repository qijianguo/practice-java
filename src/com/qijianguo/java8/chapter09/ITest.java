package com.qijianguo.java8.chapter09;

public interface ITest {

    void test01();

    void test02();

    /**
     * Java8开始，新增接口的默认方法，实现类自动继承默认方法
     * @return
     */
    default String newMethod() {
        return "New method";
    }

    /**
     * 接口中可以添加静态方法，代替之前的<接口+辅助类>的方式
     *
     * @param input
     * @return
     */
    static String format(String input) {
        return input.toLowerCase();
    }
}
