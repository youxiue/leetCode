package com.youxiue.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: xfb
 * @projectName: leetcode->RepeatedSubstringPattern459
 * @description: 459. 重复的子字符串
 * @date: 2020/08/24 10:07
 */
public class RepeatedSubstringPattern459 {


    /**
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abab"
     * <p>
     * 输出: True
     * <p>
     * 解释: 可由子字符串 "ab" 重复两次构成。
     * 示例 2:
     * <p>
     * 输入: "aba"
     * <p>
     * 输出: False
     * 示例 3:
     * <p>
     * 输入: "abcabcabcabc"
     * <p>
     * 输出: True
     * <p>
     * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
     */
    @Test
    public void test() {
        boolean b = repeatedSubstringPattern2("abcabcabcabc");
        System.out.println(b);
    }

    /**
     *  246ms  , 要死了
     */
    public boolean repeatedSubstringPattern(String s){
        int n = s.length();
        int index = n / 2;
        while (index > 0) {
            if(n % index == 0){
                String sb = s.substring(0, index);
                //String[] split = s.split(sb);
                String sr = s.replaceAll(sb, "");
                if(sr.length() == 0) return true;
            }
            index--;
        }
        return false;
    }

    /**
     * 官网解法,  再次感觉自己像个 nc
     * 95ms
     */
    public boolean repeatedSubstringPattern1(String s) {
        // 如果 s 在  s + s  中第二次出现的位置不是在 s.length() 位置, 说明s 可以由多个 子字符串组成
        // 引入 如果 s 是有 x + x 组成的话,  那 s + s 就是 x + x + x + x, 那么 s 第二次出现的位置就是 中间 那个 x+x , 是小于 s.length() 的
        return (s + s).indexOf(s, 1) != s.length();
    }


    /**
     * KMP 解法
     * 看不懂 wc
     */
    public boolean repeatedSubstringPattern2(String s) {
        return kmp(s + s, s);
    }

    public boolean kmp(String query, String pattern) {
        int n = query.length(); // 被查询字符串长度
        int m = pattern.length(); // 目标字符串长度
        int[] fail = new int[m]; // 创建一个目标字符串长度的数组
        // 里面全部填充 -1
        Arrays.fill(fail, -1);

        for (int i = 1; i < m; ++i) {
            int j = fail[i - 1]; //
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }

        int match = -1;
        for (int i = 1; i < n - 1; ++i) {
            while (match != -1 && pattern.charAt(match + 1) != query.charAt(i)) {
                match = fail[match];
            }
            if (pattern.charAt(match + 1) == query.charAt(i)) {
                ++match;
                if (match == m - 1) {
                    return true;
                }
            }
        }

        return false;
    }
}
