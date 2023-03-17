package com.youxiue.matrix;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1605. 给定行和列的和求可行矩阵
 * 给你两个非负整数数组 rowSum 和 colSum ，其中 rowSum[i] 是二维矩阵中第 i 行元素的和， colSum[j] 是第 j 列元素的和。
 * 换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。
 * 请找到大小为 rowSum.length x colSum.length 的任意 非负整数 矩阵，且该矩阵满足 rowSum 和 colSum 的要求。
 * 请你返回任意一个满足题目要求的二维矩阵，题目保证存在 至少一个 可行矩阵。
 * <p>
 * 示例 1：
 * 输入：rowSum = [3,8], colSum = [4,7]
 * 输出：[[3,0],
 * [1,7]]
 * 解释：
 * 第 0 行：3 + 0 = 3 == rowSum[0]
 * 第 1 行：1 + 7 = 8 == rowSum[1]
 * 第 0 列：3 + 1 = 4 == colSum[0]
 * 第 1 列：0 + 7 = 7 == colSum[1]
 * 行和列的和都满足题目要求，且所有矩阵元素都是非负的。
 * 另一个可行的矩阵为：[[1,2],
 * [3,5]]
 * <p>
 * 示例 2：
 * 输入：rowSum = [5,7,10], colSum = [8,6,8]
 * 输出：[[0,5,0],
 * [6,1,0],
 * [2,0,8]]
 * <p>
 * 示例 3：
 * 输入：rowSum = [14,9], colSum = [6,9,8]
 * 输出：[[0,9,5],
 * [6,0,3]]
 * <p>
 * 示例 4：
 * 输入：rowSum = [1,0], colSum = [1]
 * 输出：[[1],
 * [0]]
 * <p>
 * 示例 5：
 * 输入：rowSum = [0], colSum = [0]
 * 输出：[[0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= rowSum.length, colSum.length <= 500
 * 0 <= rowSum[i], colSum[i] <= 108
 * sum(rowSum) == sum(colSum)
 *
 * @author youxiue
 * @since 2023/3/14 10:20
 */
public class RestoreMatrix1605 {


    @Test
    public void test() {
        System.out.println(Arrays.deepToString(restoreMatrix(new int[]{3, 8}, new int[]{4, 7})));
        System.out.println(Arrays.deepToString(restoreMatrix(new int[]{5, 7, 10}, new int[]{8, 6, 8})));
        System.out.println(Arrays.deepToString(restoreMatrix(new int[]{14, 9}, new int[]{6, 9, 8})));
        System.out.println(Arrays.deepToString(restoreMatrix(new int[]{1, 0}, new int[]{1})));
        System.out.println(Arrays.deepToString(restoreMatrix(new int[]{0}, new int[]{0})));
    }

    /**
     * 自己的解法, 每个位置都填入当前能填的最大值.
     *
     * 执行用时：9 ms, 在所有 Java 提交中击败了44.27%的用户
     * 内存消耗：49.4 MB, 在所有 Java 提交中击败了47.33%的用户
     */
    public int[][] my(int[] rowSum, int[] colSum) {
        // 行
        int row = rowSum.length;
        // 列
        int col = colSum.length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 比较剩余的行或列的值, 填入当前能填入的最大值(哪个小, 哪个就是能填入的最大值, 不能超过最小的)
                res[i][j] = Math.min(rowSum[i], colSum[j]);
                // 则剩余的和需要减去已经填入的
                rowSum[i] -= res[i][j];
                colSum[j] -= res[i][j];
            }
        }
        return res;
    }

    /**
     * 官网算法
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：49.4 MB, 在所有 Java 提交中击败了45.04%的用户
     */
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int n = rowSum.length, m = colSum.length;
        int[][] matrix = new int[n][m];
        int i = 0, j = 0;
        while (i < n && j < m) {
            int v = Math.min(rowSum[i], colSum[j]);
            matrix[i][j] = v;
            rowSum[i] -= v;
            colSum[j] -= v;
            // 如果当前行剩余的和为0, 则行号+1, 移到下一行去
            if (rowSum[i] == 0) {
                ++i;
            }
            // 如果当前列剩余的和为0 , 则列号+1, 移到下一列
            if (colSum[j] == 0) {
                ++j;
            }
        }
        return matrix;
    }
}
