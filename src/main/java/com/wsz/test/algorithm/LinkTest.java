package com.wsz.test.algorithm;

// 链表算法
public class LinkTest {
    // 单联表节点
    private static class Node {
        public Node(int val) {
            this.val = val;
        }

        int val;
        Node next;
        Node pre;
    }
    // 分组反转
    public static Node groupReverse(Node h,int k) {
        Node head = getReverseEndNode(h,k);
        Node nextGroupHead = reverseNode(h,k);
        if(nextGroupHead == null){
            h.next = null;
            return head;
        }
        Node end = h;
        while (nextGroupHead != null){
            end.next = getReverseEndNode(nextGroupHead,k);
            end =nextGroupHead;
            nextGroupHead = reverseNode(nextGroupHead,k);
        }
        return head;
    }
    public static Node getReverseEndNode(Node start,int k ){
        Node curr = start;
        if(curr != null && curr.next == null){
            return curr;
        }
        Node pre = null;
        while (curr != null && --k > 0){
            pre = curr;
            curr = curr.next;
        }
        if(curr == null){
            return pre;
        }
        return curr;
    }
    // 单链表反转
    public static Node reverseNode(Node start) {
        Node cur = start;
        Node next;
        Node pre = null;
        while (cur != null) {
            // 记下当前处理节点的下一个节点
            next = cur.next;
            // 当前节点的next指针指向pre
            cur.next = pre;
            // pre来到当前指针
            pre = cur;
            // 当前指针来到next
            cur = next;
        }
        return pre;
    }
    public static Node reverseDeNode(Node h){
        if(h == null){
            return null;
        }
        Node pre = null;
        Node next;
        while(h != null){
            next = h.next;
            h.next = pre;
            h.pre = next;
            pre = h;
            h = next;
        }
        return pre;
    }
    public static Node reverseNode(Node start, int k) {
        Node cur = start;
        Node next;
        Node pre = null;
        while (cur != null && --k >= 0) {
            // 记下当前处理节点的下一个节点
            next = cur.next;
            // 当前节点的next指针指向pre
            cur.next = pre;
            // pre来到当前指针
            pre = cur;
            // 当前指针来到next
            cur = next;
        }
        if(cur == null){
            // group不够 下一次不用在分组反转
            return null;
        }
        // 下一个start节点
        return cur;
    }

    public static void main(String[] args) {
        Node h = new Node(1);
        h.next = new Node(2);
        h.next.next = new Node(3);
        h.next.next.next = new Node(4);
        h.next.next.next.next = new Node(5);
        h.next.next.next.next.next = new Node(6);
        h.next.next.next.next.next.next = new Node(7);
        h.next.next.next.next.next.next.next = new Node(8);
        h.next.next.next.next.next.next.next.next = new Node(9);
        h.next.next.next.next.next.next.next.next.next = new Node(10);
        // 打印一下反转前的链表节点
        Node curr = h;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
        System.out.println("=========");
        Node n = reverseNode(h);
        // 打印一下反转后的链表节点
        while (n != null) {
            System.out.println(n.val);
            n = n.next;
        }
        System.out.println("=========");
        curr = groupReverse(h,3);
        // 打印一下反转后的链表节点
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }
}
