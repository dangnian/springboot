package com.dangnian.springboot.algorithm.leetcode;

public class MyList {

    /**
     * 头插法
     */
    public static void headInsert(ListNode head, ListNode newHead) {
        ListNode oldHead = head;
        head = newHead;
        newHead.next = oldHead;
    }

    /**
     * 尾插法
     */
    public static void tailInsert(ListNode tail, ListNode newTail) {
        ListNode oldTail = tail;
        tail = newTail;
        tail.next = null;
        oldTail.next = tail;
    }

    /**
     * 遍历
     */
    public static void traverse(ListNode head) {
        while(head != null) {
            System.out.println(head.node);
            head = head.next;
        }
    }

}
