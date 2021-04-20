package com.imooc.test;

/**
 * @author Ace
 * @create 2020-11-28
 */
public class Print {

    static  Integer num = 0;

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            while (true) {
                if (num < 10000) {
                    if (num % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "------------" + num);
                        num++; //3897
                    }
                } else {
                    break;
                }
            }
        });
        thread.setName("偶数");
        thread.start();

        Thread thread1 = new Thread(() -> {
            while (true) {
                if (num < 10000) {
                    if (num % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + "------------" + num);
                        num++; //3896
                    }
                } else {
                    break;
                }
            }
        });
        thread1.setName("奇数");
        thread1.start();

    }

}
