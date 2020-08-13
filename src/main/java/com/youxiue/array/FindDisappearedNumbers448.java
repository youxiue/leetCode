package com.youxiue.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: xfb
 * @projectName: leetcode->FindDisappearedNumbers448
 * @description: 448. 找到所有数组中消失的数字
 * @date: 2020/08/08 16:31
 */
public class FindDisappearedNumbers448 {

    /**
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     * <p>
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     * <p>
     * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * [4,3,2,7,8,2,3,1]
     * <p>
     * 输出:
     * [5,6]
     */

    @Test
    public void test() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> list = findDisappearedNumbers1(nums);
        System.out.println(list);

    }

    // 超出时间限制 ?
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(i + 1);
        }
        for (int num : nums) {
            list.remove(new Integer(num));
        }
        return list;
    }


    // 7ms
    public List<Integer> findDisappearedNumbers1(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            // 遍历集合, 将值对应索引位置的值置为负,
            int temp = Math.abs(nums[i]) - 1; // 这里减一是因为 集合中的值是从1 到nums.length
            if (nums[temp] > 0) {
                nums[temp] *= -1;
            }
        }
        // 再遍历集合, 哪个索引对应的值没有置为负, 则说明该索引没有出现在集合中
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }
        return list;
    }

}
