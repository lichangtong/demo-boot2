package com.example.demo.demoboot2.java8test;

/**
 * @Auther: lichangtong
 * @Date: 2019-10-11 09:19
 * @Description:
 */
public class Apple {
    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    private String color;
    private int weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
