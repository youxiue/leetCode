package com.youxiue.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: xfb
 * @projectName: leetcode->MoveZeroes283
 * @description: 283. 移动零
 * @date: 2020/08/17 13:52
 */
public class MoveZeroes283 {

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * <p>
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */

    @Test
    public void test() {

        //int[] nums = {0, 1, 0, 3, 12};
        int[] nums = {0, 0, 1};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 两次遍历,
     * 第一次将所有不为0 的值前移
     * 第二次将不为0 后面的值都赋予0
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }


        int j = 0; // 记录不为0 的数
        for (int i = 0; i < nums.length; i++) {
            // 如果 值不为0  则将值前移,  每有一个不为0 的j 就 + 1
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }

        // 后面的值都赋予0
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 一次遍历
     * 还是将 不为0 的值 往左不断的堆放, 不过这里会和 被交换的元素交换位置.
     */
    public void moveZeroes1(int[] nums) {
        if (nums == null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }
}
