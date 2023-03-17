package com.youxiue.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 2389. 和有限的最长子序列
 * 给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
 * <p>
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
 * <p>
 * 子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,2,1], queries = [3,10,21]
 * 输出：[2,3,4]
 * 解释：queries 对应的 answer 如下：
 * - 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
 * - 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
 * - 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,3,4,5], queries = [1]
 * 输出：[0]
 * 解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 1000
 * 1 <= nums[i], queries[i] <= 106
 *
 * @author youxiue
 * @since 2023/3/17 13:41
 */
public class AnswerQueries2389 {

    @Test
    public void test() {
        System.out.println(Arrays.toString(answerQueries(new int[]{4, 5, 2, 1}, new int[]{3, 10, 21})));
        System.out.println(Arrays.toString(answerQueries(new int[]{2, 3, 4, 5}, new int[]{1})));
    }

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了58.97%的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了69.60%的用户
     */
    public int[] my(int[] nums, int[] queries) {
        // 对nums排序
        Arrays.sort(nums);
        int[] res = new int[queries.length];

        // 用最小的值相加, 如果值小于queries, 则为最大长度
        for (int i = 0; i < queries.length; i++) {
            int length = 0;
            int temp = 0;
            for (int num : nums) {
                temp += num;
                if (temp > queries[i]) {
                    break;
                } else {
                    length++;
                }
            }
            res[i] = length;
        }
        return res;
    }

    /**
     * 官方解法: 二分查找法
     * @param nums
     * @param queries
     * @return
     */
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        // 和的数组, f[i] 表示前 i 个元素之和
        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            f[i + 1] = f[i] + nums[i];
        }
        int[] answer = new int[m];
        for (int i = 0; i < m; i++) {
            answer[i] = binarySearch(f, queries[i]) - 1;
        }
        return answer;
    }

    /**
     * 二分查找法, 从`和的数组`中找到比目标值大的值,
     * @param f
     * @param target
     * @return
     */
    public int binarySearch(int[] f, int target) {
        int low = 1, high = f.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (f[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
