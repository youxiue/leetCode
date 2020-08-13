package com.youxiue.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: xfb
 * @projectName: leetcode->FirstMissingPositive41
 * @description: TODO
 * @date: 2020/08/08 17:36
 */
public class FirstMissingPositive41 {

    /**
     * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,0]
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: [3,4,-1,1]
     * 输出: 2
     * 示例 3:
     * <p>
     * 输入: [7,8,9,11,12]
     * 输出: 1
     *  
     * <p>
     * 提示：
     * <p>
     * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
     */
    @Test
    public void test() {
        int[] nums = {7, 8, 9, 11, 12};
        //int[] nums = {3,4,-1,1};
        //int[] nums = {1, 2, 0};
        int i = firstMissingPositive(nums);
        System.out.println(i);
    }

    /**
     * 先取最小正整数为1 , 然后如果有和result相同的,则将最小正整数result+1,
     * 如果已经比result大了 则说明后面的都是比result大的, 则result就是最小正整数
     */
    public int firstMissingPositive(int[] nums) {
        int result = 1;
        Arrays.sort(nums);
        for (int num : nums){
            if (num == result) result += 1;
            else if (num > result) return result;
        }
        return result;
    }


}
