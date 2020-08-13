package com.youxiue.array;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: xfb
 * @projectName: leetcode->ThirdMax414
 * @description: TODO
 * @date: 2020/08/06 17:36
 */
public class ThirdMax414 {

    /**
     * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
     * <p>
     * 示例 1:
     * 输入: [3, 2, 1]
     * 输出: 1
     * 解释: 第三大的数是 1.
     * <p>
     * 示例 2:
     * 输入: [1, 2]
     * 输出: 2
     * 解释: 第三大的数不存在, 所以返回最大的数 2 .
     * <p>
     * 示例 3:
     * 输入: [2, 2, 3, 1]
     * 输出: 1
     * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
     * 存在两个值为2的数，它们都排第二。
     */

    // 6/7ms
    public int thirdMax(int[] nums) {

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list.size() >= 3 ? list.get(list.size()-3) : list.get(list.size()-1);
    }

    // 5ms
    public int thirdMax2(int[] nums){
        // 维护一个只有3个元素的TreeSet，如果大于三个元素就就把Set中的最小最小值remove掉。
        // 最后如果Set中元素小于3就返回Set最大值，否则返回最小值。
        if (nums == null || nums.length == 0) throw new RuntimeException("error");

        TreeSet<Integer> set = new TreeSet<>();
        for (Integer elem : nums) {
            set.add(elem);
            if (set.size() > 3) set.remove(set.first());
        }

        return set.size() < 3 ? set.last() : set.first();   // set.last() 里面最大的元素
    }


    @Test
    public void test() {
        int[] nums = {-2147483648,1,1}; //-2147483648~2147483647
        int i = thirdMax2(nums);
        System.out.println(i);
    }



    private long MIN = Long.MIN_VALUE;    // MIN代表没有在值

    // 1ms
    public int thirdMax3(int[] nums) {
        if (nums == null || nums.length == 0) throw new RuntimeException("nums is null or length of 0");
        int n = nums.length;

        int one = nums[0];
        long two = MIN;
        long three = MIN;

        for (int i = 1; i <  n; i++) {
            int now = nums[i];
            if (now == one || now == two || now == three) continue;    // 如果存在过就跳过不看
            if (now > one) {
                three = two;
                two = one;
                one = now;
            } else if (now > two) {
                three = two;
                two = now;
            } else if (now > three) {
                three = now;
            }
        }

        if (three == MIN) return one;  // 没有第三大的元素，就返回最大值
        return (int)three;
    }

    //@Test
    //public void test2() {
    //    int a = -2147483648 - 1;
    //    System.out.println(a);
    //}
}
