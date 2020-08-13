package com.youxiue.array;

import org.junit.Test;

/**
 * @author: xfb
 * @projectName: leetcode->FindPoisonedDuration495
 * @description: TODO
 * @date: 2020/08/06 17:17
 */
public class FindPoisonedDuration495 {
    /**
     * 在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄，他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。现在，给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间，你需要输出艾希的中毒状态总时长。
     * <p>
     * 你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。
     * <p>
     *  
     * <p>
     * 示例1:
     * <p>
     * 输入: [1,4], 2
     * 输出: 4
     * 原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
     * 第 4 秒初，提莫再次攻击艾希，使得艾希获得另外 2 秒中毒时间。
     * 所以最终输出 4 秒。
     * 示例2:
     * <p>
     * 输入: [1,2], 2
     * 输出: 3
     * 原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
     * 但是第 2 秒初，提莫再次攻击了已经处于中毒状态的艾希。
     * 由于中毒状态不可叠加，提莫在第 2 秒初的这次攻击会在第 3 秒末结束。
     * 所以最终输出 3 。
     */

    @Test
    public void test() {
        int[] timeSeries = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int duration = 1;
        int poisonedDuration = findPoisonedDuration1(timeSeries, duration);
        System.out.println(poisonedDuration);
    }

    public int findPoisonedDuration(int[] timeSeries, int duration) {

        int result = 0;
        int temp = 0;
        if (timeSeries != null && timeSeries.length > 0) {
            for (int i = 1; i < timeSeries.length; i++) {
                temp = timeSeries[i] - timeSeries[i - 1];
                if ((temp) >= duration) {
                    result += duration;
                } else {
                    result += temp;
                }
            }
            result += duration;
        }

        return result;
    }


    /**
     * 官网代码 , 多简洁
     */
    public int findPoisonedDuration1(int[] timeSeries, int duration) {
        int n = timeSeries.length;
        if (n == 0) return 0;

        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            total += Math.min(timeSeries[i + 1] - timeSeries[i], duration);
        }
        return total + duration;
    }
}
