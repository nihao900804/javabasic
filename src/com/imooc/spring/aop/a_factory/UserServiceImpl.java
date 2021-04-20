package com.imooc.spring.aop.a_factory;


/**
 * @author Ace
 * @create 2020-04-16
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("a_factory addUser");
    }

    @Override
    public void updateUser() {
        System.out.println("a_factory updateUser");
    }

    @Override
    public void deleteUser() {
        System.out.println("a_factory deleteUser");
    }
}
