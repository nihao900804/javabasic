package com.imooc.spring.aop.a_cglib;


import org.junit.Test;

/**
 * @author Ace
 * @create 2020-04-16
 */
public class TestCglib {

    @Test
    public void demo() {
        UserServiceImpl userService = MyBeanFactory.createService();
        userService.addUser();
        userService.updateUser();
        userService.deleteUser();
    }

}
