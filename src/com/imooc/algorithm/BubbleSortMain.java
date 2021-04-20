package com.imooc.algorithm;

import java.util.Arrays;

/**
 * @author Ace
 * @create 2020-07-16
 */
public class BubbleSortMain {

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 3, 8, 5, 7, 4, 3};
        bubbleSort(arr);

        int[] brr = {3,2,1,4,5};
        bubbleSort2(brr,2);
        Arrays.stream(brr).forEach(System.out::println);


        int[] crr = {3,2,1,4,5};
        bubbleSort3(crr,2);

    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {   // 这里说明为什么需要-1
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    private static void bubbleSort2(int[] arr, int n) {
        int j,k = n;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (j = 1; j < k; j++) {
                if (arr[j-1]> arr[j]) {
                    int temp;
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            k--;
        }
    }


    private static void bubbleSort3(int[] arr, int n) {
        int j,k;
        int flag = n;
        while (flag > 0) {
            k = flag;
            flag = 0;
            for (j = 1; j < k; j++) {
                if (arr[j-1]> arr[j]) {
                    int temp;
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    flag = j;
                }
            }
        }
    }



}
