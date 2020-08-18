package com.youxiue.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: xfb
 * @projectName: leetcode->MinMoves453
 * @description: 453. 最小移动次数使数组元素相等
 * @date: 2020/08/14 17:03
 */
public class MinMoves453 {


    /**
     * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动将会使 n - 1 个元素增加 1。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 输入:
     * [1,2,3]
     * <p>
     * 输出:
     * 3
     * <p>
     * 解释:
     * 只需要3次移动（注意每次移动会增加两个元素的值）：
     * <p>
     * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
     */
    @Test
    public void test() {
        int[] nums = {1, 2, 3}; // 3
        //int[] nums = {1,2147483647}; // 2147483646
        int i = minMoves10(nums);
        System.out.println(i);
    }


    /**
     * my 暴力解法  , 超时严重
     */
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int index = 0;
        while (nums[0] != nums[nums.length - 1]) {
            for (int i = 0; i < nums.length - 1; i++) {
                nums[i] += 1;
            }
            index++;
            Arrays.sort(nums);
        }

        return index;
    }


    /**
     * ???????? 这咋来的?
     * 看了 下面解法 的 就懂这个了
     */
    public int minMoves1(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            count += nums[i] - nums[0];
        }
        return count;
    }

    /**
     * 评论大神版
     * n-1 个元素 +1 , 相当于另一个元素 -1
     * 结果就相当于 所有值的总和 减去  所有值都变为最小值的总和 ( minNum * n )
     * 即 sum - minNum* n
     *
     * 1ms  击败 100%,  感受到了自己的愚蠢
     */
    public int minMoves10(int[] nums) {
        int sum = 0;
        int minNum = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            if (num < minNum) {
                minNum = num;
            }
        }
        return sum - (minNum * nums.length);
    }
}
