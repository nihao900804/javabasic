package com.imooc.cute.leetcode.editor.cn.easy;

//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
// 示例 1:
// 输入: ["flower","flow","flight"]
//输出: "fl"
//
//
// 示例 2:
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
//
//
// 说明:
// 所有输入只包含小写字母 a-z 。
// Related Topics 字符串

/**
 * @author Ace
 * @create 2020-08-28
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        long timeStart = System.nanoTime();
        System.out.println(timeStart);

        String[] strs = {"flower", "flow", "flight"};
        String result = longestCommonPrefix3(strs);
        System.out.println("result:" + result);

        long timeEnd = System.nanoTime();
        System.out.println(timeEnd);
        System.out.println(timeEnd - timeStart);
    }

    public static String longestCommonPrefix3(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        //暂定一个元素为最大公共前缀
        String result = strs[0];
        //遍历数组 从第二个元素开始
        for (int i = 1; i < strs.length; i++) {
            //判断是否以目前的最大公共前缀开头
            while (strs[i].indexOf(result) != 0) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    public static String longestCommonPrefix4(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = (char) strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        //取第一个元素
        String firstStr = strs[0];
        //字符串数组元素-最小长度 暂取第一个元素 ： 即 最大公共前缀的最大长度
        int length = firstStr.length();
        //遍历数组取最小长度
        for (String str : strs) {
            length = length > str.length() ? str.length() : length;
        }
        //声明最大公共前缀
        String result = "";
        while (length > 0) {
            //是否最大
            boolean isMax = true;
            //根据目前的最大长度截取第一个元素中的部分 暂定为 最大公共前缀
            String maxPrefix = firstStr.substring(0, length);
            //用目前的最大公共前缀遍历数组去判断
            for (int i = 1; i < strs.length; i++) {
                //判断元素是否以这个最大公共前缀开始
                if (!strs[i].startsWith(maxPrefix)) {
                    isMax = false;
                    break;
                }
            }
            if (isMax) {
                result = maxPrefix;
                break;
            }
            length--;
        }
        return result;
    }

}
