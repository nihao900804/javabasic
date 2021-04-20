package com.imooc.javabasic.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ace
 * @create 2020-05-20
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        int tasks = 3;
        final CountDownLatch latch = new CountDownLatch(tasks);
        ExecutorService executorService = Executors.newFixedThreadPool(tasks);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("5秒完成："+latch);
                latch.countDown();
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3秒完成："+latch);
                latch.countDown();
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1秒完成："+latch);
                latch.countDown();
            }
        });
        executorService.shutdown();
        try {
            latch.await();
            System.out.println("结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
