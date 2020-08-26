package com.youxiue.tree;

import org.junit.Test;

/**
 * @author: xfb
 * @projectName: leetcode->MinDepth111
 * @description: 111. 二叉树的最小深度
 * @date: 2020/08/21 11:08
 */
public class MinDepth111 {

    /**
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * <p>
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     *          3
     *         / \
     *        9  20
     *          /  \
     *        15   7
     * 返回它的最小深度  2.
     * <p>
     * 示例2:
     * [1,2]
     * 1
     * /
     * 2
     * 返回最小深度 2
     */

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        root.left = node1;
        //root.right = node2;
        //node1.left = node3;
        //node1.right = node4;
        //node3.left = node5;
        //node3.right = node6;
        int i = minDepth(root);
        System.out.println(i);
    }

    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        if (root.right == null && root.left == null) {
            return 1;
        } else if (root.right ==null) {
            return minDepth(root.left) + 1;
        } else if(root.left ==null){
            return minDepth(root.right) + 1;
        }else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }


    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        //这道题递归条件里分为三种情况
        // 1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
        if(root.left == null && root.right == null) return 1;
        // 2.如果左孩子和由孩子其中一个为空，那么需要返回比较大的那个孩子的深度
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        // 这里其中一个节点为空，说明m1和m2有一个必然为0，所以可以返回m1 + m2 + 1;
        if(root.left == null || root.right == null) return m1 + m2 + 1;

        // 3.最后一种情况，也就是左右孩子都不为空，返回最小深度+1即可
        return Math.min(m1,m2) + 1;
    }

    public int minDepth3(TreeNode root) {
        if(root == null) return 0;
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        //1.如果左孩子和右孩子有为空的情况，直接返回m1+m2+1
        //2.如果都不为空，返回较小深度+1
        return root.left == null || root.right == null ? m1 + m2 + 1 : Math.min(m1,m2) + 1;
    }
}
