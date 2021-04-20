package com.imooc.spring.aop.a_proxy;


import com.imooc.javabasic.collect.User;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @author Ace
 * @create 2020-04-16
 */
public class TestJDK {

    @Test
    public void demo() {
        UserService userService = MyBeanFactory.createService();
        /*userService.addUser();
        userService.updateUser();
        userService.deleteUser();*/

        MyAspect myAspect = new MyAspect();

        UserService proxy = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(), userService.getClass().getInterfaces(),
                new UserInvocationHandler(userService, myAspect));

        proxy.addUser();


    }

}
