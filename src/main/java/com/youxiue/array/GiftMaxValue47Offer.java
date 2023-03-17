package com.youxiue.array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 47. 礼物的最大价值
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 *
 * @author youxiue
 * @since 2023/3/8 11:05
 */
public class GiftMaxValue47Offer {

    @Test
    public void test() {
        int[][] a = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(maxValue(a));
    }

    /**
     * 计算路径最大值
     *
     * @param grid
     * @return
     */
    public int my(int[][] grid) {
        // 需要有个值记录最大值
        HashMap<String, Integer> map = new HashMap<>();
        map.put("maxValue", 0);
        // 记录所有到达终点的路径值
        deep(grid, 0, 0, 0, map);

        return map.get("maxValue");
    }

    private void deep(int[][] grid, int row, int col, int temp, Map<String, Integer> map) {
        temp += grid[row][col];

        if (row + 1 >= grid.length && col + 1 >= grid[0].length) {
            map.put("maxValue", Math.max(temp, map.get("maxValue")));
        } else {
            if (row + 1 < grid.length) {
                deep(grid, row + 1, col, temp, map);
            }
            if (col + 1 < grid[0].length) {
                deep(grid, row, col + 1, temp, map);
            }
        }
    }


    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i > 0) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j]);
                }
                if (j > 0) {
                    f[i][j] = Math.max(f[i][j], f[i][j - 1]);
                }
                f[i][j] += grid[i][j];
            }
        }
        return f[m - 1][n - 1];
    }

}
