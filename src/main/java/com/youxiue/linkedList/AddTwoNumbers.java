package com.youxiue.linkedList;

import org.junit.Test;

/**
 * @author: xfb
 * @projectName: leetcode->AddTwoNumbers
 * @description: TODO
 * @date: 2020/07/31 10:54
 */
public class AddTwoNumbers {

    /*  给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
        如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
        您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

        输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
        输出：7 -> 0 -> 8
        原因：342 + 465 = 807
        */
    @Test
    public void test() {
        ListNode l1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        l1.next = node2;
        node2.next = node3;


        ListNode l2 = new ListNode(5);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(4);
        l2.next = node4;
        node4.next = node5;

        ListNode listNode = addTwoNumbers(l1, l2);

    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */

    /**
     * 官网版本
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 头
        ListNode hearNode = new ListNode(0);
        ListNode t1 = l1, t2 = l2, p = hearNode;

        int carry = 0;// 作为进位数
        while (t1 != null || t2 != null) {
            int v1 = t1 != null ? t1.val : 0;
            int v2 = t2 != null ? t2.val : 0;
            int sum = carry+ v1 + v2;
            carry = sum / 10;
            p.next = new ListNode(sum % 10);

            p = p.next;
            t1 = t1 != null ? t1.next : null;
            t2 = t2 != null ? t2.next : null;
        }
        if (carry > 0) {
            p.next = new ListNode(carry);
        }
        return hearNode.next;
    }


    /**
     * 自己的版本
     * 最后 解答错误,  因为没有考虑到 数值进位超过l1 或 l2 最长的时候,
     * 这里自己想取巧, l1 或 l2 其中一个到头了 , 就将另一个的后半段直接next链接上去, 却没有考虑到 进位的时候导致的问题
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode t1 = l1.next;
        ListNode t2 = l2.next;
        boolean flag = false;

        ListNode rNode;
        int sum = l1.val + l2.val;
        if (sum >= 10) {
            flag = true;
        }
        rNode = new ListNode(sum % 10);

        ListNode rNext = rNode;

        while (t1 != null && t2 != null) {
            sum = t1.val + t2.val;
            if (flag) {
                sum = sum + 1;
            }
            flag = sum >= 10;
            rNext.next = new ListNode(sum % 10);
            rNext = rNext.next;
            if (t1.next == null && t2.next != null) {
                rNext.next = t2;
                break;
            } else if (t1.next != null && t2.next == null) {
                rNext.next = t1;
                break;
            } else if (t1.next == null) {
                break;
            }
            t1 = t1.next;
            t2 = t2.next;
        }
       /* if (flag) {
            rNext.next = new ListNode(1);
        }*/
        return rNode;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}