package com.youxiue.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: xfb
 * @projectName: leetcode->Generate118
 * @description: 118. 杨辉三角形 , 119 杨辉三角 II
 * @date: 2020/08/17 15:56
 */
public class Generate118 {

    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行
     * <p>
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     * <p>
     * 示例:
     * <p>
     * 输入: 5
     * 输出:
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1]
     * ]
     */
    @Test
    public void test() {

        List<List<Integer>> list = generate(10);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }

    // 0ms

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            // 使用Integer数组存放值, 便于控制长度
            Integer[] sonNums = new Integer[i + 1];
            // 初始化都赋值为1
            Arrays.fill(sonNums, 1);
            // 当 循环到第二轮以后的时候
            if(i > 1){
                // 获取上一个数组
                List<Integer> parentList = list.get(i - 1);
                if (parentList != null && parentList.size() > 0) {
                    for (int j = 1; j < i; j++) {
                        // 当前值 = 上一个数组的前一个值+ 当前索引对应的值
                        sonNums[j] = parentList.get(j-1)+ parentList.get(j);
                    }
                }
            }
            list.add(Arrays.asList(sonNums));
        }
        return list;
    }


    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        // 如果用户请求0行，那么他们得到0行
        if (numRows == 0) {
            return triangle;
        }

        // 设置 第一行总是[1]。
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        // 从第二行开始循环
        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            // 获取上一行的元素
            List<Integer> prevRow = triangle.get(rowNum-1);

            // 第一个元素总是1
            row.add(1);

            // 每个三角形元素(除了每行的第一个和最后一个)
            // 等于上面和左边的元素的和
            // above-and-to-the-right.
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            // 最后一个元素总是1。
            row.add(1);

            triangle.add(row);
        }

        return triangle;
    }


    /**
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     *
     *
     *
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 3
     * 输出: [1,3,3,1]
     * 进阶：
     *
     * 你可以优化你的算法到 O(k) 空间复杂度吗？
     */
    @Test
    public void test2(){
        List<Integer> row = getRow(1);
        System.out.println(row.toString());
    }

    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int temp = 1;
        for (int i = 1; i < rowIndex + 1; i++) { // 循环层数
            for (int j = 1; j < i; j++) { // 循环设置里面的值
                Integer curr = list.get(j);
                list.set(j, list.get(j) + temp);
                temp = curr;
            }
            list.add(1);
        }
        return list;
    }

}
