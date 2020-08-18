package com.youxiue.tree;

import org.junit.Test;

/**
 * @author: xfb
 * @projectName: leetcode->IsBalanced110
 * @description: 110. 平衡二叉树
 * @date: 2020/08/17 08:17
 */
public class IsBalanced110 {

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     *
     * 示例 1:
     *
     * 给定二叉树 [3,9,20,null,null,15,7]
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回 true 。
     *
     * 示例 2:
     *
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     * 返回 false 。
     *
     */

    @Test
    public void test(){

        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        //TreeNode node5 = new TreeNode(4);
        //TreeNode node6 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        //node3.left = node5;
        //node3.right = node6;
        boolean flag = isBalanced1(root);
        System.out.println(flag);
    }


    // 自顶向下的递归
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            // 求出节点的左右高度, 判断高度差不大于1, 然后再分别以左右节点为目标节点, 判断每个节点的高度差 都满足情况
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            // 获取最深的 高度
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }


    // 自下向上的递归
    public boolean isBalanced1(TreeNode root) {
        return height1(root) >= 0; // 最后判断 高度差是不是 >=0 , 如果是-1 说明不是平衡二叉树
    }

    public int height1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height1(root.left);
        int rightHeight = height1(root.right);
        // 如果 左右深度 差值> 1 则返回-1 , 如果出现一次-1, 则说明不是平衡二叉树, 一直向上返回-1
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            // 满足平衡二叉树 则返回高度. 并将高度+1
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }


}
