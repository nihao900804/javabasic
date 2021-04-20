package com.imooc.javabasic.collect;

/**
 * @author Ace
 * @create 2020-05-17
 */
public class User {
    private String name;
    private int sex;
    private int age;



    public User(String name) {
        this.name = name;
    }

    public User(int age) {
        this.setAge(age);
    }

    public User(String name, int age) {
        this.name = name;
        this.setAge(age);
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
