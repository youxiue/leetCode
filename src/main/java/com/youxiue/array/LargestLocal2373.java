package com.youxiue.array;

import org.junit.Test;

/**
 * 给你一个大小为 n x n 的整数矩阵 grid 。
 * <p>
 * 生成一个大小为(n - 2) x (n - 2) 的整数矩阵 maxLocal ，并满足：
 * <p>
 * maxLocal[i][j] 等于 grid 中以 i + 1 行和 j + 1 列为中心的 3 x 3 矩阵中的 最大值 。
 * 换句话说，我们希望找出 grid 中每个 3 x 3 矩阵中的最大值。
 * <p>
 * 返回生成的矩阵。
 * <p>
 * 示例 1：
 * <p>
 * 9   9   8   1
 * 5   6   2   6   ==>     9   9
 * 8   2   6   4   ==>     8   6
 * 6   2   2   2
 * <p>
 * 输入：grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
 * 输出：[[9,9],[8,6]]
 * 解释：原矩阵和生成的矩阵如上图所示。
 * 注意，生成的矩阵中，每个值都对应 grid 中一个相接的 3 x 3 矩阵的最大值。
 * <p>
 * 示例 2：
 * 1   1   1   1   1
 * 1   1   1   1   1           2   2   2
 * 1   1   2   1   1      ==>  2   2   2
 * 1   1   1   1   1           2   2   2
 * 1   1   1   1   1
 * <p>
 * 输入：grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
 * 输出：[[2,2,2],[2,2,2],[2,2,2]]
 * 解释：注意，2 包含在 grid 中每个 3 x 3 的矩阵中。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == grid.length == grid[i].length
 * 3 <= n <= 100
 * 1 <= grid[i][j] <= 100
 *
 * @author youxiue
 * @since 2023/3/2 9:38
 */
public class LargestLocal2373 {

    @Test
    public void test() {
        // [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
        int[][] grid = new int[][]{{9,9,8,1}, {5,6,2,6}, {8,2,6,4}, {6,2,2,2}};
        // [1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]
        // int[][] grid = new int[][]{{1,1,1,1,1}, {1, 1, 1, 1, 1}, {1, 1, 2, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        int[][] res = my1(grid);
        for (int[] re : res) {
            for (int r : re) {
                System.out.print(r);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * 暴力解法
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了23.83%的用户
     * 时间复杂度：O(n²)
     * @param grid
     * @return
     */
    public int[][] my1(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[grid.length - 2][grid.length - 2];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int max = grid[i][j];
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        max = Math.max(max, grid[x][y]);
                    }
                }
                res[i-1][j-1] = max;
            }
        }
        return res;
    }
}
