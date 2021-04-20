package com.imooc.javabasic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefelctSample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class rc = Class.forName("com.imooc.javabasic.reflect.Robot");

        //Method method = Robot.class.getDeclaredMethod("throwHello");

        Robot robot = (Robot) rc.newInstance();
        System.out.println("Class name is " + rc.getName());
        //只能拿自己的方法，拿不到继承的
        Method throwHello = rc.getDeclaredMethod("throwHello", String.class);
        //私有的就要这一步
        throwHello.setAccessible(true);
        Object str = throwHello.invoke(robot, "Bob");
        System.out.println("getHello result is " + str);
        //只能拿PUBLIC的方法(包括继承的)
        Method sayHi = rc.getMethod("sayHi", String.class);
        sayHi.invoke(robot, "Welcome");
        Field name = rc.getDeclaredField("name");
        name.setAccessible(true);
        name.set(robot, "Alice");
        sayHi.invoke(robot, "Welcome");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }
}
