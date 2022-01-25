package com.qijianguo.java8.chapter10;

import java.util.Optional;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author qijianguo
 */
public class OptionalTestDrive {

    public static void main(String[] args) {
        OptionalTestDrive drive = new OptionalTestDrive();
//        drive.print();

//        drive.filter(Optional.of("test"));

        Properties props = new Properties();
        props.put("a", 1);
        props.put("b", false);
        props.put("c", -1);
        drive.readDuration(props, "a");
        drive.readDuration(props, "b");
    }

    private void print() {
        Person person = null;
        //Optional<Person> person1 = Optional.of(person);
        Optional<Person> optPerson = Optional.ofNullable(person);
        // 名字
        Optional<String> username = optPerson.map(Person::getUsername);

        System.out.println(username.orElse("1"));

        //-------------------------
        // person.getCar().getInsurance().getName();
        Optional<Car> optCar = optPerson.map(Person::getOptCar).map(Optional::get);
        Optional<Insurance> optInsurance = optCar.map(Car::getOptInsurance).map(Optional::get);
        Optional<String> s = optInsurance.map(Insurance::getName);
        System.out.println(s);
        //-------------------------
        Optional<String> s1 = optPerson.flatMap(Person::getOptCar).flatMap(Car::getOptInsurance).map(Insurance::getName);
        System.out.println(s1);
        //-------------------------
        String s3 = s1.orElseGet(String::new);
        System.out.println("orElseGet:" + s3);
        s1.ifPresent(a -> a.toLowerCase());
        System.out.println(s1);
        String s2 = s1.orElseThrow(() -> new IllegalArgumentException("1231231"));
    }

    /**
     * 1.person或car只要一个为空就返回空
     * 2.
     * @param optPerson
     * @param optCar
     * @return
     */
    private Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> optPerson, Optional<Car> optCar) {
        /*if (optPerson.isPresent() && optCar.isPresent()) {
            return Optional.ofNullable(findCheapestInsurance(optPerson.get(), optCar.get()));
        } else {
            return Optional.empty();
        }*/
        return optPerson.flatMap(person -> optCar.map(car -> findCheapestInsurance(person, car)));
    }

    private Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance();
    }

    private void filter(Optional<String> value) {
        value.filter(v -> v.equals("test")).ifPresent(v -> System.out.println(v));
    }

    /**
     * 从props中读取name对应的value
     * 如果value为Integer类型且大于0，则返回对应的数字，否则返回0
     * @param props
     * @param name
     */
    private void readDuration(Properties props, String name) {
//        Object value = props.getOrDefault(name, 0);
//
//        int i =0;
//        try {
//            i = Integer.parseInt(String.valueOf(value));
//        } catch (NumberFormatException nfe) {
//        }
//        System.out.println("duration: " + i);

        Function<? super Object, Optional<Integer>> stringToInt = (num) -> Optional.of(OptionalUtility.StringToInt(num));
        int duration = Optional.ofNullable(props.get(name)).flatMap(stringToInt).filter(n -> n > 0).orElse(0);
        System.out.println("duration: " + duration);

    }
}
