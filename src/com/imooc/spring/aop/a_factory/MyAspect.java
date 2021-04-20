package com.imooc.spring.aop.a_factory;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 切面类中确定通知，需要实现不同接口，接口就是规范，从而就确定方法名称
 *    采用的是"环绕通知"  MethodInterceptor
 * @author Ace
 * @create 2020-04-16
 */
public class MyAspect implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("前进");

        //手动执行目标方法
        Object object = methodInvocation.proceed();

        System.out.println("后退");

        return object;
    }
}
