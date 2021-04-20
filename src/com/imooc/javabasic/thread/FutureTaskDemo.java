package com.imooc.javabasic.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<String>(new MyCallable());
        new Thread(task).start();
        if (!task.isDone()) {
            System.out.println("task has not finished, please wait!");
        }

        /**
         * top 查看cpu 进程占用情况
         * ps -mp pid -o Thread,tid,time 查看进程下的线程情况
         * printf "%x\n" tid 将线程ID转换为16进制
         * jstack pid -> xxx.log 导出dump日志
         * 根据转换后的线程ID去定位问题代码
         */


        System.out.println("task return: " + "1");
        System.out.println("task return: " + task.get());
    }
}
