package com.imooc.javabasic.singleton;

/**
 * 双重检测
 * @author Ace
 * @create 2020-06-02
 */
public class DoubleCheckSingleton {


    private static volatile DoubleCheckSingleton instance;

    private DoubleCheckSingleton(){};

    public static DoubleCheckSingleton getInstance() {
        //横向检测  加快速递
        if (instance == null) {
            //保证线程安全
            synchronized(DoubleCheckSingleton.class) {
                //纵向检测  防止多次初始化
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }






}
