package com.imooc.cute.leetcode.editor.cn.easy;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足：
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
// 注意空字符串可被认为是有效字符串。
//
// 示例 1:
// 输入: "()"
//输出: true
//
//
// 示例 2:
// 输入: "()[]{}"
//输出: true
//
// 示例 3:
// 输入: "(]"
//输出: false
//
// 示例 4:
// 输入: "([)]"
//输出: false
//
// 示例 5:
// 输入: "{[]}"
//输出: true
// Related Topics 栈 字符串
/**
 * @author Ace
 * @create 2020-08-28
 */
public class ValidParentheses {

    public static void main(String[] args) {
        long timeStart = System.nanoTime();
        System.out.println(timeStart);

        String x = "";
        boolean result = isValid(x);
        System.out.println("result:"+result);

        long timeEnd = System.nanoTime();
        System.out.println(timeEnd);
        System.out.println(timeEnd - timeStart);
    }

    public static boolean isValid(String s) {
        return true;
    }

}
