package com.imooc.javabasic.collect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ace
 * @create 2020-05-17
 */
public class ArrayDemo {

    public static void test1() {
        List<User> list3 = new ArrayList<User>();
        //初始化User对象
        User user = new User();
        user.setName("小明");
        user.setSex(1);
        user.setAge(18);
        list3.add(user);
        System.out.println("list3："+list3);
        //集合拷贝
        ArrayList<User> list4 = new ArrayList<User>(list3);
        System.out.println("拷贝完之后的list4："+list4);
        //集合拷贝完成之后修改User对象的值
        user.setAge(20);
        System.out.println("修改User对象年龄之后的lsit4："+list4);
    }

    public static void test2() {
        ArrayList<String> list = new ArrayList<String>(10);
        list.add("小明");
        list.add("卖托儿索的小火柴");
        list.add("海阔天空");
        System.out.println(list.size());
        //list.add(5,"逆天而行");
        System.out.println("list：" + list);
    }

    public static void main(String[] args) {
        ArrayDemo.test1();
        //ArrayDemo.test2();
    }

}
