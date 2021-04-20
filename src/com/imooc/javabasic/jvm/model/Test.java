package com.imooc.javabasic.jvm.model;

public class Test {

    public static void main(String[] args) {
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch);
    }

}
