package com.imooc.javabasic.reflect;

public class ClassLoaderChecker {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader m = new MyClassLoader("E:\\PROJECT\\learn\\JAVA\\","MyClassLoader");
        Class c = m.loadClass("Wali");
        System.out.println("customize:"+c.getClassLoader());
        System.out.println("app:"+c.getClassLoader().getParent());
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("extension:"+c.getClassLoader().getParent().getParent());
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("bootstrap:"+c.getClassLoader().getParent().getParent().getParent());
        c.newInstance();
    }
}
