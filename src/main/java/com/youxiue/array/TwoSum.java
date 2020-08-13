package com.youxiue.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xfb
 * @projectName: leetcode->TwoSum
 * @description: TODO
 * @date: 2020/07/30 18:19
 */
public class TwoSum {

    /**
     * @title: 两数之和
     * <p>
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    @Test
    public void test() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        //int[] result = twoSum(nums, target);
        int[] result = twoSum3(nums, target);
        System.out.println(Arrays.toString(result));


    }

    /**
     * 我的弱鸡版本
     * 双重for循环
     */
    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return arr;
    }

    /**
     * 大神版本
     * 两个for循环
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Object, Integer> map = new HashMap<>();
        // 将 数组存入到 map中, 将值作为键
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            // 获取差值, 如果差值也是map 中的键, 即找到了对应的两个值
            if(map.containsKey(temp) && map.get(temp) != i){
                return new int[]{i, map.get(temp)};
            }
        }
        return null;
    }

    /**
     * 一个for循环
     * 效果一样, 不过会先找到后面的值, 然后再找到前面对面差值的索引
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Object, Integer> map = new HashMap<>();
        // 将 数组存入到 map中, 将值作为键
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];

            if(map.containsKey(temp) && map.get(temp) != i){
                return new int[]{i, map.get(temp)};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
