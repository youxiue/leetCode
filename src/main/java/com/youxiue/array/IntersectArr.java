package com.youxiue.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xfb
 * @projectName: leetcode->IntersectArr
 * @description: TODO
 * @date: 2020/08/03 16:55
 */
public class IntersectArr {


/*    给定两个数组，编写一个函数来计算它们的交集。



    示例 1：

    输入：nums1 = [1,2,2,1], nums2 = [2,2]
    输出：[2,2]
    示例 2:

    输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    输出：[4,9]


    说明：
        输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
        我们可以不考虑输出结果的顺序。

    进阶：
        如果给定的数组已经排好序呢？你将如何优化你的算法？
        如果 nums1 的大小比 nums2 小很多，哪种方法更优？
        如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？*/

    @Test
    public void test() {
        //int[] nums1 = {1, 2, 2, 1};
        //int[] nums2 = {2, 2};
        //int[] nums1 = {4, 9, 5};
        //int[] nums2 = {9, 4, 9, 8, 4};
        int[] nums1 = {4, 5, 9, 9};
        int[] nums2 = {1, 4, 8, 9, 9};

        int[] intersect = intersect2(nums1, nums2);
        System.out.println(Arrays.toString(intersect));
    }

    // 使用hashMap 存
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 获取长度较小的数组
        int[] nums = nums1.length > nums2.length ? nums2 : nums1;
        // 将长度较小的数组使用HashMap 存储, 并记录对应出现的次数
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        //然后循环长度较大的数组
        nums = nums1.length > nums2.length ? nums1 : nums2;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums) {
            //
            if (map.containsKey(i)) {
                Integer temp = map.get(i);
                if (temp > 0) {
                    list.add(i);
                    map.put(i, temp - 1);
                } else {
                    map.remove(i);
                }
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }


    /**
     * 官网的答案
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect1(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    /**
     * 进阶1 : 如果是有序数组
     * 采用双指针的方式
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0, index = 0;

        int[] arr = new int[Math.min(nums1.length, nums2.length)];
        while (i < nums1.length && j < nums2.length) {
            int iNum = nums1[i];
            int jNum = nums2[j];
            if (iNum == jNum) {
                arr[index] = iNum;
                index++;
                i++;
                j++;
            } else if (iNum < jNum) {
                //如果iNum < jNum , 则将 i++
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(arr, 0, index);
    }

    public int[] intersect3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}
