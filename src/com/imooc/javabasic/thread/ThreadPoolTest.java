package com.imooc.javabasic.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Ace
 * @desc
 * @create 2020-12-28
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //使用只能5个有限队列，corePoolSize=15, maxPoolSize=15
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 15,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(4));

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());


        //创建15个线程
        for (int i = 0; i < 20; i++) {
            executor.execute(new MyThread(i));
        }

        executor.shutdown();
    }

    public static class MyThread implements Runnable {

        private int flag;
        private boolean inQueue;

        public MyThread(int flag) {
            this.flag = flag;
        }


        @Override
        public void run() {
            System.out.println("线程编号：" + getFlag() + "   是否被加入队列：" + inQueue);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public boolean isInQueue() {
            return inQueue;
        }

        public void setInQueue(boolean inQueue) {
            this.inQueue = inQueue;
        }

        public int getFlag() {
            return flag;
        }
    }


}
