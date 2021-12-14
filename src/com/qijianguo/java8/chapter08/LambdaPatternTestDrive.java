package com.qijianguo.java8.chapter08;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * @author qijianguo
 */
public class LambdaPatternTestDrive {

    public static void main(String[] args) {
        // strategy();
        // templateMath();
        // observer();
        // processor();
        factory();
    }

    private static void factory() {
        //Product loan = ProductFactory.create("loan");
        //Product stock = ProductFactory.create("stock");
        Supplier<Product> loanSupplier = Loan::new;
        Supplier<Product> stockSupplier = Stock::new;
        Product loan = loanSupplier.get();
        Product stock = stockSupplier.get();

    }

    private static void processor() {
//        ProcessingObject<String> spellCheckerProcessing = new SpellCheckerProcessing(null);
//        ProcessingObject<String> headerTextProcessing = new HeaderTextProcessing(spellCheckerProcessing);
//        String result = headerTextProcessing.handle("lamda");
//        System.out.println(result);

        UnaryOperator<String> spellCheckerProcessing = (String input) -> input.replaceAll("lamda", "lambda");
        UnaryOperator<String> headerTextProcessing = (String input) -> "Java 8: " + input;
        Function<String, String> stringStringFunction = spellCheckerProcessing.andThen(headerTextProcessing);
        String result = stringStringFunction.apply("lamda");
        System.out.println(result);
    }

    private static void observer() {
        Subject subject = new SchoolSubject();
        System.out.println("---------------实现类方式-------------------");
        subject.registerObserver(new StudentObserver());
        subject.registerObserver(new TeacherObserver());

        subject.notifyObservers("It's a holiday starting tomorrow.");

        System.out.println("-------------Lambda方式-------------");
        Subject lambda = new SchoolSubject();
        lambda.registerObserver((String message) -> {
            System.out.println("Student received message : " + message);
        });
        lambda.registerObserver((String message) -> {
            System.out.println("Student received message : " + message);
        });
        lambda.notifyObservers("It's a holiday starting tomorrow.");

    }

    private static void templateMath() {
        OnlineBanking_v2 olb = new OnlineBanking_v2();
        olb.processCustomer(1, t -> System.out.println("邮件通知: " + t.getId()));
        olb.processCustomer(2, t -> System.out.println("短信通知: " + t.getId()));
    }

    private static void strategy() {
        // LowCaseValidator
        Validator lowerCaseValidator = s -> s.matches("[a-z]+");
        boolean abcd = lowerCaseValidator.execute("abcd");
        System.out.println("abcd: " + abcd);
        System.out.println("------------------");
        // NumericValidator
        Validator numericValidator = s -> s.matches("\\d+");
        boolean n0123 = numericValidator.execute("0123");
        System.out.println("n0123: " + n0123);
    }

}
