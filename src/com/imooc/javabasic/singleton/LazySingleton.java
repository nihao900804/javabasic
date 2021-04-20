package com.imooc.javabasic.singleton;

/**
 * 懒汉
 * @author Ace
 * @create 2020-06-02
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){};

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
