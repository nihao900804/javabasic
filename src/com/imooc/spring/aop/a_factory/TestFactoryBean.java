package com.imooc.spring.aop.a_factory;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ace
 * @create 2020-04-16
 */
public class TestFactoryBean {

    @Test
    public void demo() {
        String xmlPath = "com/imooc/spring/aop/a_factory/beans.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        UserService userService = (UserService) applicationContext.getBean("proxyService");
        userService.addUser();
        userService.updateUser();
        userService.deleteUser();
    }

}
