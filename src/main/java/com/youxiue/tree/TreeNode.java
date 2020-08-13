package com.youxiue.tree;

/**
 * @author: xfb
 * @projectName: leetcode->TreeNode
 * @description: TODO
 * @date: 2020/08/07 09:54
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
