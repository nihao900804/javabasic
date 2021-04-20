package com.imooc.javabasic.collect;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ace
 * @desc
 * @create 2021-01-29
 */
@Data
public class Child extends Adults{

    private String name;
    private int age;
    private Adults adults;

}
