package com.imooc.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Ace
 * @create 2020-09-22
 */
public class EnumTest {

    public static void main(String[] args) throws ClassNotFoundException {
        OperateEnum operateEnum = OperateEnum.valueOf("BIANGENG");
        getOperateEnum("变更操作");
    }

    private static OperateEnum getOperateEnum(String name) throws ClassNotFoundException {
        //Class<Enum> clazz = (Class<Enum>) Class.forName((OperateEnum.class).getName());
        Class<OperateEnum> classz = OperateEnum.class;
        Object[] objects = classz.getEnumConstants();
        try {
            Method getAction = classz.getMethod("getName");
            for (Object obj : objects) {
                if (name.equals(getAction.invoke(obj))) {
                    return (OperateEnum) obj;
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.getMessage();
        }
        return null;
    }

}
