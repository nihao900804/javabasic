package com.imooc.javabasic.thread;

public class ThreadTest {
    private static void attck() {
        System.out.println("Fight");
        System.out.println("Current Thread: " + Thread.currentThread().getName());


        StringBuffer sb = new StringBuffer();
        sb.append("a");

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            public void run() {
                attck();
            }
        };
        t.start();
        System.out.println("current main thread is: " + Thread.currentThread().getName());
        t.join();
        t.start();
    }

}
