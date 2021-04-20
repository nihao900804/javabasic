package com.imooc.spring.aop.a_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Ace
 * @desc
 * @create 2021-01-28
 */
public class UserInvocationHandler implements InvocationHandler {

    UserService userService;
    MyAspect myAspect;

    public UserInvocationHandler(UserService userService,MyAspect myAspect) {
        this.userService = userService;
        this.myAspect = myAspect;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        myAspect.before();
        Object object = method.invoke(userService,args);
        myAspect.after();
        return object;
    }
}
