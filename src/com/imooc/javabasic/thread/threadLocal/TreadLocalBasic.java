package com.imooc.javabasic.thread.threadLocal;

public class TreadLocalBasic {

    public static ThreadLocal<Long> x = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            System.out.println("Initial value run..");
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println(x.get());
            }
        }.start();
        x.set(100L);
        x.remove();
        System.out.println(x.get());
    }

}
