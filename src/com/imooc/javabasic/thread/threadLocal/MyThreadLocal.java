package com.imooc.javabasic.thread.threadLocal;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadLocal<T> {

    static AtomicInteger atomic = new AtomicInteger();

    Integer threadLocalHash = atomic.addAndGet(0x61c88647);

    static HashMap<Thread,HashMap<Integer,Object>> threadLocalMap = new HashMap<>();

    synchronized static HashMap<Integer,Object> getMap() {
        Thread thread = Thread.currentThread();
        if(!threadLocalMap.containsKey(thread)) {
            threadLocalMap.put(thread,new HashMap<Integer,Object>());
        }
        return threadLocalMap.get(thread);
    }

    protected T initialValue() {
        return null;
    }

    public T get() {
        HashMap map = getMap();
        if(!map.containsKey(this.threadLocalHash)) {
          map.put(this.threadLocalHash,initialValue());
        }
        return (T) map.get(this.threadLocalHash);
    }

    public void set(T v) {
        HashMap map = getMap();
        map.put(threadLocalHash,v);
    }

}
