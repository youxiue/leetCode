package com.youxiue.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: xfb
 * @projectName: leetcode->MaximumProduct628
 * @description: TODO
 * @date: 2020/08/07 15:15
 */
public class MaximumProduct628 {

    /**
     * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3]
     * 输出: 6
     * 示例 2:
     * <p>
     * 输入: [1,2,3,4]
     * 输出: 24
     * 注意:
     * <p>
     * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
     * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
     */
    @Test
    public void test() {
        int[] nums = {1, 2, -3, 4, 5};
        int i = maximumProduct1(nums);
        System.out.println(i);
    }

    /**
     * 最大值出现的可能性:
     * 1.三个最大的正整数相乘
     * 2. 两个最小的负数和一个正数相乘
     */

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3], nums[0] * nums[1] * nums[nums.length - 1]);
    }


    public int maximumProduct1(int[] nums) {
        // 定义 5个数: min1 < min2 < max1 < max2 < max3
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }

            if (num > max3) {
                max1 = max2;
                max2 = max3;
                max3 = num;
            } else if (num > max2) {
                max1 = max2;
                max2 = num;
            } else if (num > max1) {
                max1 = num;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max3);
    }


}
