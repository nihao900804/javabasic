package com.imooc.javabasic.reflect;

public class LoadDifference {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader cl = Robot.class.getClassLoader();
        Class c = Class.forName("com.imooc.javabasic.reflect.Robot");
    }
}
