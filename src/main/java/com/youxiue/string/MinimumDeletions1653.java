package com.youxiue.string;

import org.junit.Test;

/**
 * 1653. 使字符串平衡的最少删除次数
 * 给你一个字符串 s ，它仅包含字符 'a' 和 'b'。
 * <p>
 * 你可以删除 s 中任意数目的字符，使得 s 平衡 。当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
 * <p>
 * 请你返回使 s 平衡 的 最少 删除次数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aababbab"
 * 输出：2
 * 解释：你可以选择以下任意一种方案：
 * 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
 * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
 * 示例 2：
 * <p>
 * 输入：s = "bbaaaaabb"
 * 输出：2
 * 解释：唯一的最优解是删除最前面两个字符。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 105
 * s[i] 要么是 'a' 要么是 'b'
 *
 * @author youxiue
 * @since 2023/3/7 15:36
 */
public class MinimumDeletions1653 {

    @Test
    public void test() {
        System.out.println(minimumDeletions("aababbab"));
        System.out.println(minimumDeletions("bbaaaaabb"));
    }

    /**
     * 通过删除部分字符串，使得字符串达到下列三种情况之一，即为平衡状态：
     * 1. 字符串全为 “a”;
     * 2. 字符串全为 “b”；
     * 3. 字符串既有 “a”也有 “b”，且所有 “a” 都在所有 “b” 左侧。
     * 其中，为了达到第 1种情况，最少需要删除所有的 “b”。
     * 为了达到第 2 种情况，最少需要删除所有的 “a”。
     * 而第 3 种情况，可以在原字符串相邻的两个字符之间划一条间隔，删除间隔左侧所有的 “b” 和间隔右侧所有的 “a” 即可达到。
     * 用leftb 表示间隔左侧的 “b” 的数目，righta 表示间隔左侧的 “a” 的数目，leftb+righta 即为当前划分的间隔下最少需要删除的字符数。
     * 这样的间隔一共有 n−1 种，其中 n 是 s 的长度。遍历字符串s，即可以遍历n−1 种间隔，同时更新 leftb 和 righta 的数目。
     * 而上文讨论的前两种情况，其实就是间隔位于首字符前和末字符后的两种特殊情况，可以加入第 3 种情况一并计算。
     *
     * @param s
     */
    public int my(String s) {
        int rightA = 0;
        int leftB = 0;
        // 当指针在最左侧的是时候, 右边的a的个数
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                rightA++;
            }
        }
        int res = rightA;
        // 循环, 指针右移, 判断指针左右两边的a和b的数量,
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                // 如果是a, 则右边的a--
                rightA--;
            } else {
                // 如果不是a, 则左边的b++
                leftB++;
            }
            //
            res = Math.min(res, rightA + leftB);
        }
        return res;

    }

    /**
     * 作者: endlesscheng
     *
     * 问：为什么把 if-else 写成 (c - 'a') * 2 - 1 就会快很多？
     *
     * 答：CPU 在遇到分支（条件跳转指令）时会预测代码要执行哪个分支，如果预测正确，CPU 就会继续按照预测的路径执行程序。
     * 但如果预测失败，CPU 就需要回滚之前的指令并加载正确的指令，以确保程序执行的正确性。
     *
     * 对于本题的数据，字符 ‘a’ 和 ‘b’ 可以认为是随机出现的，在这种情况下分支预测就会有 50% 的概率失败。
     * 失败导致的回滚和加载操作需要消耗额外的 CPU 周期，如果能用较小的代价去掉分支，对于本题的情况必然可以带来效率上的提升。
     *
     * 注意：这种优化方法往往会降低可读性，最好不要在业务代码中使用。
     *
     * @param s
     * @return
     */
    public int minimumDeletions(String s) {
        char[] chars = s.toCharArray();
        int del = 0;
        for (char c : chars)
            // 统计 'a' 的个数,  如果是a则加1
            // 'a' = 97    'b' = 98,
            // 'b' - 'a' = 1,      'b' - 'b' = 0
            del += 'b' - c;
        int ans = del;
        for (char c : chars) {
            // 'a' -> -1    'b' -> 1
            // 'a' - 'a' = 0    'b'- 'a' = 1
            // 结果就是 如果是a的话del就减1, 如果是b的话就加1
            del += (c - 'a') * 2 - 1;
            ans = Math.min(ans, del);
        }
        return ans;
    }
}
