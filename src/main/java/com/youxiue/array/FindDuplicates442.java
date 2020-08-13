package com.youxiue.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xfb
 * @projectName: leetcode->FindDuplicates442
 * @description: 442. 数组中重复的数据
 * @date: 2020/08/08 17:15
 */
public class FindDuplicates442 {

    /**
     * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
     * <p>
     * 找到所有出现两次的元素。
     * <p>
     * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
     * <p>
     * 示例：
     * <p>
     * 输入:
     * [4,3,2,7,8,2,3,1]
     * <p>
     * 输出:
     * [2,3]
     */

    @Test
    public void test() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> list = findDuplicates(nums);
        System.out.println(list);
    }

    // 7ms
    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> list = new ArrayList<>();

        // 循环集合, 将值对应的索引的值置为负, 如果后面再次碰到这个负值, 说明两个值指向同一个索引, 即为相同值
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] *= -1;
            } else if (nums[index] < 0) {
                list.add(index + 1);
            }
        }
        return list;
    }
}
