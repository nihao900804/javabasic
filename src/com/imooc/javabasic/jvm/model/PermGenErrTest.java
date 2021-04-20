package com.imooc.javabasic.jvm.model;

import java.util.Random;

public class PermGenErrTest {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            getRandomString(1000000).intern();
        }
        System.out.println("Mission Complete!");
    }

    /**
     *
     * @param length
     * @return
     */
    private static String getRandomString(int length) {
        String str="dasdadadqwrewrqdsfdwertyiuophgcvbnmnnxgfguyturtdfguhfhgfhjmhngbvxczrew";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(69);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
