package com.imooc.javabasic.thread;

import java.util.concurrent.*;

/**
 * @author Ace
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService = new ThreadPoolExecutor( 80,200,60L,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>( 5000 ),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName( "doPlay parallel thread" );
                    return thread;
                },new ThreadPoolExecutor.AbortPolicy());

        executorService.execute(new MyRunnable("nihao"));

        Future<String> future = executorService.submit(new MyCallable());
        if (!future.isDone()) {
            System.out.println("task has not finished, please wait!");
        }
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
