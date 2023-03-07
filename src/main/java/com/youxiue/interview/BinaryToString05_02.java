package com.youxiue.interview;

import org.junit.Test;

/**
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 * <p>
 * 示例1:
 * 输入：0.625
 * 输出："0.101"
 * <p>
 * 示例2:
 * 输入：0.1
 * 输出："ERROR"
 * 提示：0.1无法被二进制准确表示
 * <p>
 * 提示：
 * 32位包括输出中的 "0." 这两位。
 * 题目保证输入用例的小数位数最多只有 6 位
 *
 * @author youxiue
 * @since 2023/3/2 11:52
 */
public class BinaryToString05_02 {

    @Test
    public void test() {
        System.out.println(printBin(0.625));
        System.out.println(printBin(0.1));
    }

    public String printBin(double num) {
        StringBuffer sb = new StringBuffer("0.");
        while (sb.length() < 32 && num != 0) {
            // num * 2, 整数部位既是二进制的
            num *= 2;
            // 获取整数部分
            int temp = (int) num;
            sb.append(temp);
            // 将整数部分减去
            num -= temp;
        }
        return num == 0 ? sb.toString() : "ERROR";
    }
}
