package com.imooc.spring.aop.a_proxy;

import java.lang.reflect.Proxy;

/**
 * @author Ace
 * @create 2020-04-16
 */
public class MyBeanFactory {

    public static UserService createService() {
        //1.目标类
        final UserServiceImpl userService = new UserServiceImpl();
        //2.切面类
        final MyAspect myAspect = new MyAspect();
        /*3.代理类：将目标类(切入点)和切面类(通知)结合-->切面
         *  Proxy.newProxyInstance
         *     参数1：loader，类加载器，动态代理类 运行时创建，任何类都需要类加载器将其加载到内存
         *           一般情况:当前类.class.getClassLoader();
         *     参数2：interfaces 代理类需要实现的所有接口
         *           方式1：目标类实例.getClass().getInterfaces() 只能获取自己接口，不能获取父类接口
         *           方式2：new Class[]{UserService.class}
         *           例如：jdbc驱动 --> DriverManager 获得接口Connection
         *     参数3：InvocationHandler 处理类，接口，必须进行实现类，一般采用匿名内部类
         *          提供invoke方法，代理类的每一个方法执行时，都将调用一次invoke
         *                参数31：Object proxy 代理对象
         *                参数32：Method method 代理对象当前执行的方法的描述对象(反射)
         *                      执行方法名 method.getName()
         *                      执行方法  method.invoke(对象，实际参数)
         *                参数33：Object[] args 方法实际参数
         */
        UserService proxyService = (UserService) Proxy.newProxyInstance(MyBeanFactory.class.getClassLoader(),
                userService.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    //前执行
                    myAspect.before();
                    //执行目标类的方法
                    Object obj = method.invoke(userService, args);
                    //后执行
                    myAspect.after();
                    return obj;
                });
        //return proxyService;

        //目标类
        UserService userService1 = new UserServiceImpl();
        //切面类
        MyAspect myAspect1 = new MyAspect();
        //代理类
        //参数一 类加载器 这边可以用当前类的类加载器
        //参数二 代理类需要实现的接口
        //参数三 InvocationHandler 处理类，接口，必须进行实现类，一般采用匿名内部类
        UserService proxyService1 = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                userService1.getClass().getInterfaces(),
                (o, method, args) -> {
                    myAspect1.before();
                    Object object = method.invoke(userService1, args);
                    myAspect1.after();
                    return object;
                });
        return proxyService;

    }

}
