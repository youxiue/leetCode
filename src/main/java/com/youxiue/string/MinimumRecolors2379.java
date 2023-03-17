package com.youxiue.string;

import org.junit.Test;

/**
 * 2379. 得到 K 个黑块的最少涂色次数
 * 给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。字符 'W' 和 'B' 分别表示白色和黑色。
 * 给你一个整数 k ，表示想要 连续 黑色块的数目。
 * 每一次操作中，你可以选择一个白色块将它 涂成 黑色块。
 * 请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。
 *
 * 示例 1：
 *
 * 输入：blocks = "WBBWWBBWBW", k = 7
 * 输出：3
 * 解释：
 * 一种得到 7 个连续黑色块的方法是把第 0 ，3 和 4 个块涂成黑色。
 * 得到 blocks = "BBBBBBBWBW" 。
 * 可以证明无法用少于 3 次操作得到 7 个连续的黑块。
 * 所以我们返回 3 。
 *
 * 示例 2：
 *
 * 输入：blocks = "WBWBBBW", k = 2
 * 输出：0
 * 解释：
 * 不需要任何操作，因为已经有 2 个连续的黑块。
 * 所以我们返回 0 。
 *
 * 提示：
 *
 * n == blocks.length
 * 1 <= n <= 100
 * blocks[i] 要么是 'W' ，要么是 'B' 。
 * 1 <= k <= n
 *
 * @author youxiue
 * @since 2023/3/9 15:58
 */
public class MinimumRecolors2379 {

    @Test
    public void test() {
        System.out.println(minimumRecolors("WBBWWBBWBW", 7));
        System.out.println(minimumRecolors("WBWBBBW", 2));
        System.out.println(minimumRecolors("BWWWBB", 6));
    }


    /**
     * 暴力解法
     * 执行用时：2 ms, 在所有 Java 提交中击败了 24.80%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了88.19%的用户
     */
    public int my(String blocks, int k) {
        int n = blocks.length();
        int min = k;
        for (int i = 0; i <= n - k; i++) {
            int temp = 0;
            for (int j = i; j < i + k; j++) {
                if (blocks.charAt(j) == 'W') {
                    temp++;
                }
            }
            min = Math.min(temp, min);
        }
        return min;
    }


    /**
     * 官方解法, 滑动窗口
     * 执行用时：1 ms, 在所有 Java 提交中击败了58.27%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了50.79%的用户
     */
    public int minimumRecolors(String blocks, int k) {
        // left是窗口左索引, right是窗口右索引
        int cnt = 0, left = 0, right = 0;
        // 从0 到k-1 位置 (即k个长度)的w的数量
        for (; right < k; right++) {
            cnt += blocks.charAt(right) == 'W' ? 1 : 0;
        }
        int res = cnt;
        while (right < blocks.length()) {
            // 窗口右移, 左边是w就+ 1 , 右边是w就减1
            cnt += blocks.charAt(right) == 'W' ? 1 : 0;
            cnt -= blocks.charAt(left) == 'W' ? 1 : 0;
            res = Math.min(res, cnt);
            left++;
            right++;
        }

        return res;
    }
}
