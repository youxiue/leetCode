package com.youxiue.graph;

import org.junit.Test;

import java.util.*;

/**
 * 1615. 最大网络秩
 * n 座城市和一些连接这些城市的道路 roads 共同组成一个基础设施网络。每个 roads[i] = [ai, bi] 都表示在城市 ai 和 bi 之间有一条双向道路。
 * <p>
 * 两座不同城市构成的 城市对 的 网络秩 定义为：与这两座城市 直接 相连的道路总数。如果存在一条道路直接连接这两座城市，则这条道路只计算 一次 。
 * <p>
 * 整个基础设施网络的 最大网络秩 是所有不同城市对中的 最大网络秩 。
 * <p>
 * 给你整数 n 和数组 roads，返回整个基础设施网络的 最大网络秩 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 0---1---2
 * |   |
 * |---3
 * 输入：n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
 * 输出：4
 * 解释：城市 0 和 1 的网络秩是 4，因为共有 4 条道路与城市 0 或 1 相连。位于 0 和 1 之间的道路只计算一次。
 * <p>
 * 示例 2：
 * 0---1---2---4
 * |   |   |
 * |---3---|
 * 输入：n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
 * 输出：5
 * 解释：共有 5 条道路与城市 1 或 2 相连。
 * <p>
 * 示例 3：
 * 0---1---2---3       6---5---7
 * |
 * 4
 * 输入：n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
 * 输出：5
 * 解释：2 和 5 的网络秩为 5，注意并非所有的城市都需要连接起来。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 100
 * 0 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 2
 * 0 <= ai, bi <= n-1
 * ai != bi
 * 每对城市之间 最多只有一条 道路相连
 *
 * @author youxiue
 * @since 2023/3/15 13:44
 */
public class MaximalNetworkRank1615 {


    @Test
    public void test() {
        System.out.println(my(4, new int[][]{{0, 1}, {0, 3}, {1, 2}, {1, 3}}));
        System.out.println(my(5, new int[][]{{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 3}, {2, 4}}));
        System.out.println(my(8, new int[][]{{0, 1}, {1, 2}, {2, 3}, {2, 4}, {5, 6}, {5, 7}}));
    }


    /**
     * my 暴力枚举算法
     * 执行用时：37 ms, 在所有 Java 提交中击败了8.26%的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了82.17%的用户
     */
    public int my(int n, int[][] roads) {
        // 使用map当做链表, 存入每个顶点的所有连接点, set长度就是道路数
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] road : roads) {
            if (!map.containsKey(road[0])) {
                map.put(road[0], new HashSet<>());
            }
            if (!map.containsKey(road[1])) {
                map.put(road[1], new HashSet<>());
            }
            map.get(road[0]).add(road[1]);
            map.get(road[1]).add(road[0]);
        }
        // 找到和最长的两个
        int maxSize = 0;
        for (Map.Entry<Integer, Set<Integer>> oe : map.entrySet()) {
            for (Map.Entry<Integer, Set<Integer>> ie : map.entrySet()) {
                if (!oe.getKey().equals(ie.getKey())) {
                    int x = oe.getValue().size() + ie.getValue().size();
                    // 如果oe的set中包含 ie的顶点, 则说明重复计数了一次, 需要减一
                    if (oe.getValue().contains(ie.getKey())) {
                        x = x - 1;
                    }
                    maxSize = Math.max(x, maxSize);
                }
            }
        }
        return maxSize;
    }


    /**
     * 官方: 枚举法
     * 执行用时：3 ms, 在所有 Java 提交中击败了91.30%的用户
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了90.43%的用户
     */
    public int maximalNetworkRank(int n, int[][] roads) {
        // 使用矩阵图, 有道路的地方标志为true
        boolean[][] connect = new boolean[n][n];
        // 一维数组, 记录每个节点的度
        int[] degree = new int[n];
        for (int[] v : roads) {
            connect[v[0]][v[1]] = true;
            connect[v[1]][v[0]] = true;
            // 如果顶点有道路链接, 则度+1
            degree[v[0]]++;
            degree[v[1]]++;
        }

        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 计算两个顶点的度之和, 如果这两个顶点之间有路, 则减一
                int rank = degree[i] + degree[j] - (connect[i][j] ? 1 : 0);
                maxRank = Math.max(maxRank, rank);
            }
        }
        return maxRank;
    }

    /**
     * 官方: 贪心算法
     * 执行用时：4 ms, 在所有 Java 提交中击败了34.78%的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了88.26%的用户
     * @param n
     * @param roads
     * @return
     */
    public int maximalNetworkRank2(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        int[] degree = new int[n];
        for (int[] road : roads) {
            int x = road[0], y = road[1];
            connect[x][y] = true;
            connect[y][x] = true;
            degree[x]++;
            degree[y]++;
        }

        int first = -1, second = -2;
        List<Integer> firstArr = new ArrayList<Integer>();
        List<Integer> secondArr = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (degree[i] > first) {
                // 比对找到最大的道路数, 然后将之前最大的变成第二大
                second = first;
                secondArr = new ArrayList<Integer>(firstArr);
                first = degree[i];
                firstArr.clear();
                firstArr.add(i);
            } else if (degree[i] == first) {
                firstArr.add(i);
            } else if (degree[i] > second) {
                // 如果道路数小于第一 ,但是大于第二, 则该道路数即为第二
                secondArr.clear();
                second = degree[i];
                secondArr.add(i);
            } else if (degree[i] == second) {
                secondArr.add(i);
            }
        }
        if (firstArr.size() == 1) {
            // 如果最大的道路数只有一个, 则循环判断最大和第二大的之间是否存在没有连接的,
            int u = firstArr.get(0);
            for (int v : secondArr) {
                if (!connect[u][v]) {
                    return first + second;
                }
            }
            return first + second - 1;
        } else {
            // 如果最大度的有多个 最大度的集合的长度为a, 则共有 a(a-1)/2 种组合,
            int m = roads.length;
            if (firstArr.size() * (firstArr.size() - 1) / 2 > m) {
                return first * 2;
            }
            for (int u : firstArr) {
                for (int v : firstArr) {
                    if (u != v && !connect[u][v]) {
                        return first * 2;
                    }
                }
            }
            return first * 2 - 1;
        }
    }


}
