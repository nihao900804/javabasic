package com.imooc.javabasic.thread.threadLocal;

public class ThreadLocalTest {

    static MyThreadLocal<Long> v = new MyThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(v.get());
            },i+"").start();
        }

        MyThreadLocal<Long> v = new MyThreadLocal<Long>();
        System.out.println(v.get());
    }

}
