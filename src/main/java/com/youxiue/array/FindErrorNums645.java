package com.youxiue.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author: xfb
 * @projectName: leetcode->FindErrorNums645
 * @description: TODO
 * @date: 2020/08/07 15:49
 */
public class FindErrorNums645 {
    /**
     * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
     * <p>
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,2,2,4]
     * 输出: [2,3]
     * 注意:
     * <p>
     * 给定数组的长度范围是 [2, 10000]。
     * 给定的数组是无序的。
     */
    @Test
    public void test() {
        //int[] nums = {1, 2, 2, 4};
        //int[] nums = {1, 1};
        int[] nums = {2, 2};
        int[] errorNums = findErrorNums(nums);
        System.out.println(Arrays.toString(errorNums));
    }

    // 使用排序 11ms
    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int dup = -1, missing = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                dup = nums[i];
            else if (nums[i] > nums[i - 1] + 1)
                missing = nums[i - 1] + 1;
        }
        return new int[] {dup, nums[nums.length - 1] != nums.length ? nums.length : missing};
    }

    // 使用map  20ms
    public int[] findErrorNums1(int[] nums) {
        // 先都存入map 中, 并记录出现次数

        int a = -1;
        int b = -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            /*if (map.containsKey(num)){
                map.put(num, map.get(num)+1);
            }else {
                map.put(num, 1);
            }*/
            map.put(num, map.getOrDefault(num , 0)+1);
        }

        for (int i = 1; i <= nums.length; i++) {
            if(map.containsKey(i)){
                if(map.get(i) > 1){// 如果次数大于1 说明是重复的
                    a = i;
                }
            }else { // 如果map 中没有这个数, 则说明缺失
                b = i;
            }
        }

        return new int[]{a, b};
    }

    // 使用arr  1ms
    public int[] findErrorNums2(int[] nums) {


        int a = -1;
        int b = -1;
        int[] arr = new int[nums.length+1];
        for (int num : nums) {
            arr[num] += 1;
        }


        for (int i = 1; i < arr.length; i++) {
            if(arr[i] >1){
                a = i;
            }
            if (arr[i] == 0) {

                b = i;
            }
        }

        return new int[]{a, b};
    }
}
