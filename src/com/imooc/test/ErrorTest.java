package com.imooc.test;

/**
 * @author Ace
 * @create 2020-09-22
 */
public class ErrorTest {

    public static void main(String[] args) {
        int i = 0;
        int x = 1;

        int z = callBack(i,x);

        System.out.println(z);

    }

    private static int callBack(int i, int x) {
        try {
            int y = x / i;
        } catch (Exception e) {
            e.printStackTrace();
            return x;
        }
        return 26;
    }

}
