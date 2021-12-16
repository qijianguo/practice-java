package com.qijianguo.java8.chapter09;

/**
 * Java8 解决菱形继承问题的3条原则
 * 1.类或父类中显式声明的方法，其优先级高于所有的默认方法：C中的print方法优先级最高
 * 2.当1不满足时：选择提供最具体实现的默认方法的接口：如果C中没有print方法，若接口B继承接口A，则B的优先级更高
 * 3.当1和2都不满足时：在类中覆盖该方法，显式地选择使用哪个接口中提供的默认方法：如果1和2都不满足，就需要C实现一个接口
 *
 * @author qijianguo
 */
public class C implements A, B{

    public static void main(String[] args) {
        C c = new C();
        c.print();
    }

    @Override
    public void print() {
        // 1.实现类中的方法优先级最高
        //System.out.println("print C.");
        // 3.显示的选择要使用哪个默认方法的实现：如果1和2都不满足，就需要C实现一个接口
        A.super.print();
    }
}
