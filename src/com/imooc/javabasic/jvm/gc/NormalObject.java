package com.imooc.javabasic.jvm.gc;


public class NormalObject {

    public String name;
    public NormalObject(String name) {
        this.name = name;
    }

    public NormalObject() {

    }

    @Override
    protected void finalize() {
        System.out.println("Finalizing obj " + name);
    }
}
