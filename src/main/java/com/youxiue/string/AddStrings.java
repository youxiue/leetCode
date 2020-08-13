package com.youxiue.string;

import org.junit.Test;

/**
 * @author: xfb
 * @projectName: leetcode->AddStrings
 * @description: TODO
 * @date: 2020/08/03 08:59
 */
public class AddStrings {

/*    给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

    注意：

    num1 和num2 的长度都小于 5100.
    num1 和num2 都只包含数字 0-9.
    num1 和num2 都不包含任何前导零。
    你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。*/


    @Test
    public void test() {
        String num1 = "987321";
        String num2 = "999999";
        String resultNum = addStrings(num1, num2);
        System.out.println(resultNum);
    }

    //byte short int long float double boolean char
    // char

    private String addStrings(String num1, String num2) {
        // 从低位向高位加 ,所以先获取字符串的最后一位数字,从后往前
        int i = num1.length() - 1, j = num2.length() - 1, curr = 0;

        StringBuffer sb = new StringBuffer();
        while (i >= 0 || j >= 0 || curr != 0) {
            // 获取每位对应的值
            int a = i >= 0 ? num1.charAt(i) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = a + b + curr;
            curr = sum / 10;
            sb.append(sum % 10);
            i--;
            j--;
        }

        sb.reverse();
        return sb.toString();
    }




}
