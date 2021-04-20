package com.imooc.javabasic.singleton;

/**
 * 饿汉
 * @author Ace
 * @create 2020-06-02
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){};

    public static HungrySingleton getInstance() {
        return instance;
    }

}
