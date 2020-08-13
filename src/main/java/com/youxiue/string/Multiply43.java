package com.youxiue.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: xfb
 * @projectName: leetcode->Multiply43
 * @description: TODO
 * @date: 2020/08/13 11:26
 */
public class Multiply43 {

    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * <p>
     * 示例 1:
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * <p>
     * 示例 2:
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     * 说明：
     * <p>
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     */

    @Test
    public void test() {
        String multiply = multiply1("123456789", "987654321");  // 121932631112635269
        System.out.println(multiply);
    }

    /**
     * @Author xfb
     * @Description 移位乘法, 每次移位结果相加
     * @Date 2020/8/13 15:53
     * <p>
     * 28ms 18%,  40M 11%   垃圾
     **/
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();

        int i = chars1.length - 1;

        StringBuilder sb;
        String mul = "";


        while (i >= 0) {
            int n1 = chars1[i] - '0';
            int j = chars2.length - 1;
            int curr = 0;
            sb = new StringBuilder();
            while (j >= 0) {
                int n2 = chars2[j] - '0';
                int temp = n1 * n2 + curr;
                curr = temp / 10;
                sb.append(temp % 10);
                j--;
            }
            if (curr != 0) {
                sb.append(curr);
            }
            sb.reverse();
            for (int n = chars1.length - i - 1; n > 0; n--) {
                sb.append("0");
            }
            mul = sum(mul, sb.toString());
            i--;
        }
        return mul;
    }

    private String sum(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();

        int i = chars1.length - 1, j = chars2.length - 1, curr = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? chars1[i] - '0' : 0;
            int n2 = j >= 0 ? chars2[j] - '0' : 0;
            int sum = n1 + n2 + curr;
            curr = sum / 10;
            sb.append(sum % 10);
            i--;
            j--;
        }
        if (curr != 0) {
            sb.append(curr);
        }
        sb.reverse();
        return sb.toString();
    }


    /**
     * 令m 和n分别表示 num1 和num2 的长度, 并且他们均不为0,  则 num1 和num2的乘积的长度为 m+n-1 或 m+n
     * 因此创建长度为 m+n 长度的数组ansArr用户存储乘积, 对于 任意 0<=i<m 和0<=j<m,  num1[i]*num2[j]的结果位于ansArr[i+j+1], 如果ansArr[i+j+1]>=10, 则将进位部分加到 ansArr[i+j]
     *
     */
    public String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10; //  如果 ansArr[i+j+1]≥10，则将进位部分加到 ansArr[i+j]。
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0; // 如果最高位为0  ,则舍弃
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }


    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }


}
