package com.youxiue.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xfb
 * @projectName: leetcode->FindShortestSubArray697
 * @description: TODO
 * @date: 2020/08/08 15:42
 */
public class FindShortestSubArray697 {

    /**
     * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
     * <p>
     * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1, 2, 2, 3, 1]
     * 输出: 2
     * 解释:
     * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
     * 连续子数组里面拥有相同度的有如下所示:
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组[2, 2]的长度为2，所以返回2.
     * <p>
     * 示例 2:
     * <p>
     * 输入: [1,2,2,3,1,4,2]
     * 输出: 6
     * 最短连续子数组[2,2,3,1,4,2], 长度为6
     */

    // 我的 21ms
    public int findShortestSubArray(int[] nums) {
        ArrayList<Integer> list;
        // 1. 定义Map key为值, value使用ArrayList记录每个数出现的位置,  list.size()即为长度
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                list = map.get(nums[i]);
                list.add(i);
                map.put(nums[i], list);
            } else {
                list = new ArrayList<>();
                list.add(i);
                map.put(nums[i], list);
            }
        }

        // 2.定义count记录最大次数, len记录最大度下的最小长度.
        int count = 0, len = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            // 然后找出次数最大的,  再获取都是最大次数但是之间的差值最小值
            ArrayList<Integer> temp = entry.getValue();
            if (temp.size() > count) {
                // 如果比之前的最大次数还要大, 则设置新的最大次数, 并获取长度
                count = temp.size();
                len = temp.get(temp.size() - 1) - temp.get(0);
            } else if (temp.size() == count) {
                // 如果和之前的最大次数相同, 则取长度最小的.
                len = temp.get(temp.size() - 1) - temp.get(0) < len ? temp.get(temp.size() - 1) - temp.get(0) : len;
            }
        }
        // 3. 坐标差值+1 才是最小数组长度
        return len + 1;
    }

    /**
     * 官方 30ms
     */
    public int findShortestSubArray1(int[] nums) {
        //定义3个map ,left存放第一次出现的位置, right 存放最后一次出现的位置, count存放出现的次数
        Map<Integer, Integer> left = new HashMap(),
                right = new HashMap(), count = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (left.get(x) == null) left.put(x, i);
            right.put(x, i);
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        //然后获取最大的次数
        int ans = nums.length;
        int degree = Collections.max(count.values());

        // 如果和最大次数相同, 则获取最小的长度.
        for (int x: count.keySet()) {
            if (count.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 2, 3, 1};
        //int[] nums = {1, 2, 2, 3, 1, 4, 2};
        int i = findShortestSubArray(nums);
        System.out.println(i);
    }
}
