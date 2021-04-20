package com.imooc.javabasic.thread;

import com.imooc.javabasic.jvm.gc.NormalObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author Ace
 * @create 2020-04-21
 */
public class ForkJoinTest extends RecursiveTask<Integer> {

    private static final int thresheld = 2;

    private int start;

    private int end;

    public ForkJoinTest(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean flag = (end - start) <= thresheld;
        System.out.println("还未执行 (end-start):"+ (end-start) + "  end:"+end + " start:"+start);
        //如果未执行的任务数量小于等于了线程数量，则表示未完成的任务很少了，可以直接进行计算
        if(flag){
            for(int i = start; i <= end; i++){
                System.out.println("执行 start："+i + " end:"+end);
                sum += i;
            }
        }else{          //如果未执行的任务数量大于等于线程数量，则表示未完成的任务还有很多，使用ForkJoin
            int middle = (start + end) / 2;     //平均分配任务数量
            ForkJoinTest forkJoinTest1 = new ForkJoinTest(start, middle);       //执行前半部分的任务
            ForkJoinTest forkJoinTest2 = new ForkJoinTest(middle + 1, end);       //执行后半部分的任务

            //开始执行子任务
            forkJoinTest1.fork();
            forkJoinTest2.fork();

            //得到结果
            int res1 = forkJoinTest1.join();
            int res2 = forkJoinTest2.join();

            sum = res1 + res2;
        }

        return sum;
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTest task = new ForkJoinTest(1, 10);//计算1-100的和

        Future<Integer> result = forkJoinPool.submit(task); //执行这个任务

        try {
            System.out.printf(result.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
