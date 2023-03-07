package com.youxiue.array;

import org.junit.Test;

/**
 * TODO
 *
 * @author youxiue
 * @since 2023/2/16 16:50
 */
public class Arr2341NumberOfPairs {
    /**
     * 给你一个下标从 0 开始的整数数组 nums 。在一步操作中，你可以执行以下步骤：
     * <p>
     * 从 nums 选出 两个 相等的 整数
     * 从 nums 中移除这两个整数，形成一个 数对
     * 请你在 nums 上多次执行此操作直到无法继续执行。
     * <p>
     * 返回一个下标从 0 开始、长度为 2 的整数数组 answer 作为答案，其中 answer[0] 是形成的数对数目，answer[1] 是对 nums 尽可能执行上述操作后剩下的整数数目。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,3,2,1,3,2,2]
     * 输出：[3,1]
     * 解释：
     * nums[0] 和 nums[3] 形成一个数对，并从 nums 中移除，nums = [3,2,3,2,2] 。
     * nums[0] 和 nums[2] 形成一个数对，并从 nums 中移除，nums = [2,2,2] 。
     * nums[0] 和 nums[1] 形成一个数对，并从 nums 中移除，nums = [2] 。
     * 无法形成更多数对。总共形成 3 个数对，nums 中剩下 1 个数字。
     * 示例 2：
     * <p>
     * 输入：nums = [1,1]
     * 输出：[1,0]
     * 解释：nums[0] 和 nums[1] 形成一个数对，并从 nums 中移除，nums = [] 。
     * 无法形成更多数对。总共形成 1 个数对，nums 中剩下 0 个数字。
     * 示例 3：
     * <p>
     * 输入：nums = [0]
     * 输出：[0,1]
     * 解释：无法形成数对，nums 中剩下 1 个数字。
     * 提示：
     * <p>
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     */

    /**
     * 我的 实现
     * @param nums
     * @return
     */
    public int[] numberOfPairs(int[] nums) {
        int[] result = new int[2];
        result[1] = nums.length;
        int current = 0;
        while (current < nums.length) {
            if (nums[current] != -1) {
                for (int i = current + 1; i < nums.length; i++) {
                    if (nums[current] == nums[i]) {
                        nums[i] = -1;
                        nums[current] = -1;
                        result[0] = result[0] + 1;
                        break;
                    }
                }
            }
            current++;
        }
        result[1] = nums.length - result[0] * 2;
        return result;
    }

    /**
     * 他人的实现
     * @param nums
     * @return
     */
    public int[] numberOfPairs2(int[] nums) {
        int[] counts = new int[101];    // 存储每种数字个数的数组
        for(int num: nums){
            counts[num]++;  // 存储
        }
        int pairs = 0;
        int remain = 0;
        for(int count: counts){
            pairs += count / 2;     // 每种数字生成的数对个数
            remain += count % 2;    // 每种数字剩余的数字个数
        }
        return new int[]{pairs, remain};
    }

    @Test
    public void main() {
        // int[] nums = {1, 3, 2, 1, 3, 2, 2};
        // int[] nums = {1, 1};
        int[] nums = {31, 49, 96, 49, 94, 31, 72, 28, 63};
        // int[] nums = {1};
        int[] result = numberOfPairs(nums);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
