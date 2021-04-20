package com.imooc.spring.aop.a_cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @author Ace
 * @create 2020-04-16
 */
public class MyBeanFactory {

    public static UserServiceImpl createService() {
        //1.目标类
        final UserServiceImpl userService = new UserServiceImpl();
        //2.切面类
        final MyAspect myAspect = new MyAspect();
        //3.代理类
        //3.1核心类
        Enhancer enhancer = new Enhancer();
        //3.2确定父类
        enhancer.setSuperclass(userService.getClass());
        /*3.3设置回调函数，MethodInterceptor接口 等效jdk InvocationHandler接口
         *  intercept() 等效 jdk invoke()
         *    参数1 参数2 参数3  与 invoke一致
         */
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            //前
            myAspect.before();
            //执行目标类的方法
            Object obj = method.invoke(userService, objects);
            //执行代理类的父类即目标类  (目标类和代理类  父子关系)
            methodProxy.invokeSuper(o, objects);
            //后
            myAspect.after();
            return obj;
        });
        //3.4创建代理
        UserServiceImpl proxService = (UserServiceImpl) enhancer.create();
        return proxService;
    }

}
