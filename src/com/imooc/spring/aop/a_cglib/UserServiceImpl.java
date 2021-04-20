package com.imooc.spring.aop.a_cglib;

import com.imooc.spring.aop.a_proxy.UserService;

/**
 * @author Ace
 * @create 2020-04-16
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("a_cglib addUser");
    }

    @Override
    public void updateUser() {
        System.out.println("a_cglib updateUser");
    }

    @Override
    public void deleteUser() {
        System.out.println("a_cglib deleteUser");
    }
}
