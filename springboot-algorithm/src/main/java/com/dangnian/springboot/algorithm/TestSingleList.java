package com.dangnian.springboot.algorithm;



public class TestSingleList {


    /**
     * 单链表处理
     */
    static class ListNode {

        public int data;
        public ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }

    }

    public static void arrange(ListNode head) {
        ListNode prev = head;
        ListNode current = head.next;
        while (current.next != null) {
            ListNode secondLastNode = head;
            ListNode lastNode = head.next;
            // 获取最后一个节点和倒数第二个节点
            while(lastNode.next != null) {
                secondLastNode = secondLastNode.next;
                lastNode = lastNode.next;
            }
            prev.next = lastNode;
            lastNode.next = current;
            secondLastNode.next = null;
            prev = current;
            current = current.next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = null;
        ListNode head = node1;
        while (head.next != null) {
            System.out.print(head.data);
            head = head.next;
        }
        ListNode head2 = node1;
        arrange(head2);
        System.out.println();
        ListNode head3 = node1;
        while (head3.next != null) {
            System.out.print(head3.data);
            head3 = head3.next;
        }
    }


}
