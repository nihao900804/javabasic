package com.imooc.javabasic.extendDemo;

class Animals {
    int age = 10;

    void enjoy() {
        System.out.println("Animals enjoy!");
    }

}

class Dogg extends Animals {
    int age = 20;
    int weight;

    void enjoy() {
        System.out.println("Dog enjoy!");
    }
}

public class TestPolymorphism {
    public static void main(String[] args) {
        Animals a = new Animals();
        a.enjoy();
        System.out.println(a.age);

        Dogg d = new Dogg();
        d.enjoy();
        System.out.println(d.age);

        Animals d1 = new Dogg();
        d1.enjoy();
        System.out.println(d1.age);
        Dogg s = (Dogg) d1;
        System.out.println(s.age);

    }

}
