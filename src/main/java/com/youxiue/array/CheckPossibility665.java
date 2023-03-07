package com.youxiue.array;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author: xfb
 * @projectName: leetcode->CheckPossibility665
 * @description: 665. 非递减数列
 * @date: 2020/08/17 10:24
 */
public class CheckPossibility665 {

    /**
     * 给你一个长度为n的整数数组，请你判断在 最多 改变1 个元素的情况下，该数组能否变成一个非递减数列。
     * <p>
     * 我们是这样定义一个非递减数列的：对于数组中所有的i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
     * <p>
     *
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [4,2,3]
     * 输出: true
     * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
     * 示例 2:
     * <p>
     * 输入: nums = [4,2,1]
     * 输出: false
     * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
     *
     * <p>
     * 说明：
     * <p>
     * 1 <= n <= 10 ^ 4
     * - 10 ^ 5<= nums[i] <= 10 ^ 5
     */
    @Test
    public void test() {
        //int[] nums = {4, 2, 3}; // true
        //int[] nums = {3, 4, 2, 3}; // false
        int[] nums = {5, 7, 1, 8}; // true
        boolean b = checkPossibility(nums);
        System.out.println(b);
    }

    public boolean checkPossibility(int[] nums) {

        if (nums.length < 3) {
            return true;
        }

        int curr = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                curr++;
                if (curr > 1) {
                    return false;
                }
                if (i - 1 >= 0 && nums[i - 1] > nums[i + 1]) {
                    // 如果前面的值大于后面的值  例:  3, 4, 2 , 则将后面的值 赋值为当前值 -> 3, 4, 4
                    nums[i + 1] = nums[i];
                } else {
                    // 如果前面的值小于后面的值, 或前面没有值, 则将当前值赋值为后面的值 1 , 3 , 2  -> 1, 2, 2
                    nums[i] = nums[i + 1];
                }
            }

        }
        return true;
    }


}
