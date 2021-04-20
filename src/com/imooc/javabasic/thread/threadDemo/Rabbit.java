package com.imooc.javabasic.thread.threadDemo;


public class Rabbit extends Animal {

    public Rabbit() {
        setName("兔子");
    }

    @Override
    public void runing() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                //兔子速度
                int dis = 5;
                length -= dis;

                System.out.println("兔子跑了" + dis + "米，距离终点还有" + length + "米 ");
                if (length <= 0) {
                    length = 0;
                    System.out.println("兔子获得了胜利");
                    // 给回调对象赋值，让乌龟不要再跑了
                    if (calltoback != null) {
                        System.out.println("兔子");
                        calltoback.win();
                    }
                }
                if ((2000 - length) % 20 == 0) {	// 每20米休息一次,休息时间是1秒
                    sleep(1000);
                } else {				//没0.1秒跑5米
                    sleep(100);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("乌龟赢了");
            e.printStackTrace();
        }
    }

}
