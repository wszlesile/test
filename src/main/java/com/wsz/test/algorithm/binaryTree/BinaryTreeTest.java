package com.wsz.test.algorithm.binaryTree;

import java.util.*;

public class BinaryTreeTest {
    private static class Node {
        int val;
        Node leftNode;
        Node rightNode;
    }

    // 先序便利二叉树
    public static void printPre(Node h) {
        Stack<Node> stack = new Stack<>();
        stack.push(h);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.val);
            if (node.rightNode != null) {
                stack.push(node.rightNode);
            }
            if (node.leftNode != null) {
                stack.push(node.leftNode);
            }
        }
    }
    // 中序遍历
    public static void printMid(Node h) {
        Stack<Node> stack = new Stack<>();
        while (!stack.empty() || h != null) {
            if (h != null) {
                stack.push(h);
                h = h.leftNode;
            } else {
                h = stack.pop();
                System.out.println(h.val);
                h = h.rightNode;
            }
        }
    }
    // 后序遍历
    public static void printPos(Node h){
        Stack<Node> stack = new Stack<>();
        stack.push(h);
        Node c;
        while (!stack.empty()){
            c = stack.peek();
            if(c.leftNode != null && ((c.leftNode != h && c.rightNode != h) || h == null)){
                stack.push(c.leftNode);
            }else if(c.rightNode != null &&  c.rightNode != h){
                stack.push(c.rightNode);
            }else{
               h =  stack.pop();
               System.out.println(h.val);
               h = c;
            }
        }
    }
    // 宽度遍历
    public static void printLevel(Node h){
        Queue<Node> queue = new LinkedList<>();
        queue.add(h);
        if(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println(node.val);
            if(node.leftNode != null){
                queue.add(node.leftNode);
            }
            if(node.rightNode != null){
                queue.add(node.rightNode);
            }
        }
    }
    // 获取二叉树的宽度 使用map
    public static int getMaxLevelUseMap(Node h){
        Queue<Node> queue = new LinkedList<>();
        queue.add(h);
        Map<Node,Integer> levelMap = new HashMap<>(16);
        levelMap.put(h,1);
        // 最大宽度
        int max = 0;
        // 当前层
        int currLevel = 1;
        // 当前层节点数
        int currLevelNodes = 0;
        while (!queue.isEmpty()){
            Node currNode = queue.poll();
            int tempLevel = levelMap.get(currNode);
            System.out.println(currNode.val);
            if(currNode.leftNode != null){
                queue.add(currNode.leftNode);
                levelMap.put(currNode.leftNode,currLevel+1);
            }
            if(currNode.rightNode != null){
                queue.add(currNode.rightNode);
                levelMap.put(currNode.rightNode,currLevel+1);
            }
            if(tempLevel == currLevel){
                 // 还在当前层
                currLevelNodes++;
            }else{
                // 层数变化了 需要结算上一层
                max = Math.max(max,currLevelNodes);
                currLevelNodes = 1;
                currLevel++;
            }
        }
        return Math.max(max,currLevelNodes);
    }
    // 二叉树最大宽度计算 不使用map
    public static int getMaxLevelUnUseMap(Node h){
      Queue<Node> queue = new LinkedList<>();
      queue.add(h);
      Node currLevelEndNode = h;
      Node nextLevelEndNode = null;
      int currLevelNodes = 0;
      int max = 0;
      while (!queue.isEmpty()){
          Node currNode = queue.poll();
          if(currNode.leftNode != null){
              queue.add(currNode.leftNode);
              nextLevelEndNode = currNode.leftNode;
          }
          if(currNode.rightNode != null){
              queue.add(currNode.rightNode);
              nextLevelEndNode = currNode.rightNode;
          }
          currLevelNodes++;
          if(currNode == currLevelEndNode){
              // 当前层遍历完了
              max = Math.max(max,currLevelNodes);
              currLevelEndNode = nextLevelEndNode;
              currLevelNodes =0;
          }
      }
      return max;
    }
}
