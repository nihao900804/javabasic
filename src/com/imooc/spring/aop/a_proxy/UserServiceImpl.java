package com.imooc.spring.aop.a_proxy;

/**
 * @author Ace
 * @create 2020-04-16
 */
public class UserServiceImpl implements UserService{
    @Override
    public void addUser() {
        System.out.println("a_proxy addUser");
    }

    @Override
    public void updateUser() {
        System.out.println("a_proxy updateUser");
    }

    @Override
    public void deleteUser() {
        System.out.println("a_proxy deleteUser");
    }
}
