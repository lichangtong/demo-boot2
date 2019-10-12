package com.example.demo.demoboot2.java8test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * @Auther: lichangtong
 * @Date: 2019-10-11 09:19
 * @Description:
 */
public class Java8Test {
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static void main(String[] args) {
        List<Apple> result = new ArrayList<>();
        Apple green = new Apple("green", 100);
        Apple red = new Apple("red", 130);
        Apple green1 = new Apple("green", 150);
        Apple black = new Apple("black", 80);
        Apple yellow = new Apple("yellow", 200);

        result.add(green);
        result.add(red);
        result.add(green1);
        result.add(black);
        result.add(yellow);
        //List<Apple> vList = filterApples(result, Java8Test::isHeavyApple);
        // List<Apple> vList = filterApples(result, (Apple app) -> "green".equals(app.hashCode()));
//        List<Apple> vList = filterApples(result, (Apple app) -> app.getWeight() > 150);
//        List<Apple> vList = filterApples(result, (Apple app) -> "green".equals(app.hashCode()) && app.getWeight() > 150);
//        List<Apple> vList = filterApples(result, (Apple app) -> "green".equals(app.hashCode()) || app.getWeight() > 150);
        //顺序处理：
//        List<Apple> vList = result.stream().filter((apple -> apple.getColor().equals("green") && apple.getWeight() == 100)).collect(toList());
        //并行处理：
        List<Apple> vList = result.parallelStream().filter((apple -> apple.getColor().equals("green") && apple.getWeight() == 100)).collect(toList());
        System.out.println(vList);
    }

    public static List<Apple> filterApples(List<Apple> inventory,
                                           Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
