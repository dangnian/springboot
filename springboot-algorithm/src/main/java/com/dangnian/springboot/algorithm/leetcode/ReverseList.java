package com.dangnian.springboot.algorithm.leetcode;

/**
 * 反转链表
 */
public class ReverseList {


    /**
     * 整个反转
     * @param head
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        ListNode current = head.next;
        prev.next = null;
        while(current != null) {
            ListNode next  = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public ListNode reverseList(ListNode head, int startIndex,int endIndex) {
        if(head == null || startIndex >= endIndex) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        return null;
    }



}
