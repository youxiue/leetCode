package com.youxiue.string;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 * @author youxiue
 * @since 2023/2/24 15:35
 */
public class Str3LengthOfLongestSubstring {

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring(" "));
        System.out.println(lengthOfLongestSubstring("b"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("ab"));
    }

    /**
     * 自己的方法
     * 执行用时： 103 ms , 在所有 Java 提交中击败了 8.09% 的用户
     * 内存消耗： 43.6 MB , 在所有 Java 提交中击败了 5.04% 的用户
     *
     * @param s
     * @return
     */
    public int my1(String s) {
        // 双指针, 嵌套循环?
        int maxLength = 0;
        String[] split = s.split("");
        for (int i = 0; i < s.length(); i++) {
            Set<String> set = new HashSet<>();
            set.add(split[i]);
            boolean flag = false;
            for (int j = i + 1; j < s.length(); j++) {
                if (!set.contains(split[j])) {
                    set.add(split[j]);
                } else {
                    flag = true;
                    maxLength = Math.max(set.size(), maxLength);
                    break;
                }
            }
            if (!flag) {
                maxLength = Math.max(set.size(), maxLength);
            }
        }
        return maxLength;
    }

    /**
     * 网络大神版
     * 思路：
     * 这道题主要用到思路是：滑动窗口
     * 什么是滑动窗口？
     * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！
     * 如何移动？
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     *
     * 时间复杂度： O(n)
     * 执行用时：4 ms, 在所有 Java 提交中击败了87.06%的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了51.14%的用户
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        // 最大值
        int max = 0;
        // 左指针
        int left = -1;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)));
            }
            max = Math.max(max, i - left);
            map.put(s.charAt(i), i);
        }
        return max;
    }

}
