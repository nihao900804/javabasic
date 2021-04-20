package com.imooc.javabasic.staticDemo;

public class Test {

    static {
        System.out.println("test static");
    }

    Test() {
        System.out.println("test constructor");
    }

    Person person = new Person("Test");

    {
        System.out.println(222222);
    }

    public static void main(String[] args) {
        new MyClass();
        System.out.println("again");
        new Test();
    }

}

class Person {
    static {
        System.out.println("person static");
    }

    public Person(String str) {
        System.out.println("person " + str);
    }
}


class MyClass extends Test {
    Person person = new Person("MyClass");

    static {
        System.out.println("myclass static");
    }

    public MyClass() {
        System.out.println("myclass constructor");
    }
}