package com.imooc.spring.aop.a_proxy;

/**
 * @author Ace
 * @create 2020-04-16
 */
public class MyAspect {

    public void before() {
        System.out.println("鸡头");
    }

    public void after() {
        System.out.println("牛后");

    }
}
