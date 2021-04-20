package com.imooc.javabasic.thread;

public class NotificationDemo {
    private volatile boolean go = false;

    public static void main(String[] args) throws InterruptedException {
        final NotificationDemo test = new NotificationDemo();

        Runnable waitTask = new Runnable() {
            @Override
            public void run() {
                try {
                    test.shouldGo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finished Execution");
            }
        };

        Runnable notifyTask = new Runnable() {
            @Override
            public void run() {
                test.go();
                System.out.println(Thread.currentThread().getName() + " finished Execution");
            }
        };

        Thread t1 = new Thread(waitTask, "WT1");
        Thread t2 = new Thread(waitTask, "WT2");
        Thread t3 = new Thread(waitTask, "WT3");
        Thread t4 = new Thread(notifyTask, "NT1");

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(200);

        t4.start();

    }


    private synchronized void shouldGo() throws InterruptedException {
        while (!go) {
            System.out.println(Thread.currentThread() + " is going to wait on this object");
            wait();
            System.out.println(Thread.currentThread() + " is woken up");
        }
        go = false;
    }

    private synchronized void go() {
        while (!go) {
            System.out.println(Thread.currentThread() + " is going to notify all or one thread waiting on this object");
            go = true;
            notifyAll();
        }
    }

}
