package com.youxiue.tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: xfb
 * @projectName: leetcode->IsSameTree100
 * @description: TODO
 * @date: 2020/08/07 09:53
 */
public class IsSameTree100 {

    /**
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * <p>
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * <p>
     * 示例 1:
     * <p>
     * 输入:       1         1
     *           / \       / \
     *          2   3     2   3
     * <p>
     *          [1,2,3],   [1,2,3]
     * <p>
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:      1          1
     *          /           \
     *          2             2
     * <p>
     *      [1,2],     [1,null,2]
     * <p>
     * 输出: false
     * 示例 3:
     * <p>
     * 输入:       1         1
     *           / \       / \
     *          2   1     1   2
     * <p>
     *          [1,2,1],   [1,1,2]
     * <p>
     * 输出: false
     */

    // 深度优先算法
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }


    // 广度优先算法
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        // 定义两个链表, 分别存放 左右节点
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.offer(p);
        queue2.offer(q);
        
        while (!queue1.isEmpty() && !queue2.isEmpty()){

            TreeNode left = queue1.poll();
            TreeNode right = queue2.poll();

            if(left.val != right.val){
                return false;
            }
            // 获取 下面一层
            TreeNode left1 = left.left, right1 = left.right, left2 = right.left, right2 = right.right;
            // 先判断 结构是否相同
            if(left1 == null ^ left2 ==null){ // 如果 左子节点 不都为空 或不都不为空, 则说明不相同
                return false;
            }

            if(right1 == null ^ right2 == null){
                return false;
            }

            // 如果结构都相同了 , 则将节点加入队列, 接着循环

            // 将左边的树加入 queue1
            if(left1 !=null){
                queue1.add(left1);
            }
            if(right1 != null){
                queue1.add(right1);
            }

            // 将 右边的树加入 queue2
            if(left2 !=null){
                queue2.add(left2);
            }
            if(right2 != null){
                queue2.add(right2);
            }
        }
        // 如果最后
        return queue1.isEmpty() && queue2.isEmpty();
    }

    @Test
    public void test() {
        TreeNode root1 = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        root1.left = left1;
        root1.right = right1;


        TreeNode root2 = new TreeNode(1);
        TreeNode left2 = new TreeNode(2);
        TreeNode right2 = new TreeNode(3);
        root2.left = left2;
        root2.right = right2;

        boolean sameTree = isSameTree1(root1, root2);
        System.out.println(sameTree);

    }

}
