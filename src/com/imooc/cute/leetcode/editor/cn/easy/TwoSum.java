package com.imooc.cute.leetcode.editor.cn.easy;

import java.util.HashMap;

//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//
//
//
// 示例:
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
//
/**
 * @author Ace
 * @create 2020-08-28
 */
public class TwoSum {

    public static void main(String[] args) {
        long timeStart = System.nanoTime();
        System.out.println(timeStart);

        int[] x = {2, 7, 11, 15};
        int[] result = twoSum(x,9);
        System.out.println("result:["+result[0]+","+result[1]+"]");

        long timeEnd = System.nanoTime();
        System.out.println(timeEnd);
        System.out.println(timeEnd - timeStart);
    }

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("no solution");
        }
        /*for (int i = 0; i < nums.length-1; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    if (nums[i]+nums[j] == target) {
                        return new int[]{i,j};
                    }
                }
            }*/
        HashMap map = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            if (map.containsKey(key)) {
                return new int[] {(int) map.get(key),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("no solution");
    }

}
