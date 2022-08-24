package com.wsz.test.algorithm.binaryTree;

import com.wsz.test.algorithm.TreeNode;

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
    // 不同的二叉搜索树
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> list = new ArrayList<>();
        for(int i = 1;i<=n;i++){
            TreeNode node = new TreeNode(i);
            list.add(node);
        }
        list =  buildTree(list);
        return list;
    }
    private static List<TreeNode> buildTree(List<TreeNode> list){
        if(list == null || list.size() == 0){
            return null;
        }
        List<TreeNode> result = new ArrayList<>();
        if(list.size() == 1){
            result.add(new TreeNode(list.get(0).val,list.get(0).left,list.get(0).right));
        }
        for(int i = 0;i<list.size();i++){
            TreeNode root = list.get(i);
            List<TreeNode> leftNodes = null;
            if(i != 0){
                leftNodes = list.subList(0,i);
            }
            leftNodes = buildTree(leftNodes);
            List<TreeNode> rightNodes = null;
            if(i != list.size()-1){
                rightNodes = list.subList(i+1,list.size());
            }
            rightNodes = buildTree(rightNodes);
            if(leftNodes != null && rightNodes != null){
                // 左子树的可能性
                for(TreeNode leftNode : leftNodes){
                    for(TreeNode rightNode : rightNodes){
                        TreeNode newRoot = new TreeNode(root.val,root.left,root.right);
                        newRoot.left = new TreeNode(leftNode.val,leftNode.left,leftNode.right);
                        newRoot.right = new TreeNode(rightNode.val,rightNode.left,rightNode.right);
                        result.add(newRoot);
                    }
                }
            }else if(leftNodes != null){
                for(TreeNode leftNode : leftNodes){
                    TreeNode newRoot = new TreeNode(root.val,root.left,root.right);
                    newRoot.left = new TreeNode(leftNode.val,leftNode.left,leftNode.right);
                    result.add(newRoot);
                }
            } else if(rightNodes != null){
                // 右子树的可能性
                for(TreeNode rightNode : rightNodes){
                    TreeNode newRoot = new TreeNode(root.val,root.left,root.right);
                    newRoot.right = new TreeNode(rightNode.val,rightNode.left,rightNode.right);
                    result.add(newRoot);
                }
            }
        }
        return result;
    }
    // 恢复一颗搜索二叉树（两个节点被交换）
    public static void recoverTree(TreeNode root) {
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> nodes = new ArrayList<>();
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                nodes.add(root);
                root = root.right;
            }
        }
        TreeNode x = null;
        TreeNode y = null;
        for(int i = 0; i<nodes.size()-1;i++){
            y = nodes.get(i+1);
            if(x==null) {
                x = nodes.get(i);
            }
        }
        swap(x,y);
    }
    private static void swap(TreeNode n1,TreeNode n2){
        if(n1 == null || n2 == null){
            return;
        }
        int val = n2.val;
        n2.val = n1.val;
        n1.val = val;
    }
    // 序列化和反序列化二叉搜索树
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            // val-leftVal-rightVal,
            if(root == null){
                return "";
            }
            return recursionTree(root);
        }
        private String recursionTree(TreeNode node){
            Queue<TreeNode> queue = new LinkedList<>();
            String result = "";
            queue.add(node);
            while(!queue.isEmpty()){
                node = queue.poll();
                String temp = node.val+"";
                if(node.left != null){
                    queue.add(node.left);
                    temp += "-"+node.left.val;
                }else{
                    temp += "-null";
                }
                if(node.right != null){
                    queue.add(node.right);
                    temp += "-"+node.right.val;
                }else{
                    temp += "-null";
                }
                result +=temp+",";
            }
            return result;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            TreeNode root = null;
            String[] arr = data.split(",");
            Map<Integer,TreeNode> lookupMap = new HashMap<>();

            for(int i = arr.length-1;i>=0;i--){
                String arrStr=arr[i];
                if(arrStr.equals("")){
                    continue;
                }
                String[] valArr = arrStr.split("-");
                if(i == 0){
                    root = new TreeNode(Integer.parseInt(valArr[0]));
                    TreeNode leftNode = null;
                    if(!valArr[1].equals("null")){
                        leftNode = lookupMap.get(Integer.parseInt(valArr[1]));
                    }
                    TreeNode rightNode = null;
                    if(!valArr[2].equals("null")){
                        rightNode = lookupMap.get(Integer.parseInt(valArr[2]));
                    }
                    root.left = leftNode;
                    root.right = rightNode;
                }else{
                    TreeNode node = new TreeNode(Integer.parseInt(valArr[0]));
                    lookupMap.put(Integer.parseInt(valArr[0]),node);
                    TreeNode leftNode = null;
                    if(!valArr[1].equals("null")){
                        leftNode = lookupMap.get(Integer.parseInt(valArr[1]));
                        node.left = leftNode;
                    }
                    TreeNode rightNode = null;
                    if(!valArr[2].equals("null")){
                        rightNode = lookupMap.get(Integer.parseInt(valArr[2]));
                        node.right = rightNode;
                    }
                    if(!valArr[1].equals("null") && leftNode == null){
                        leftNode = new TreeNode(Integer.parseInt(valArr[1]));
                        node.left  = leftNode;
                        lookupMap.put(Integer.parseInt(valArr[1]),leftNode);
                    }
                    if(!valArr[2].equals("null") && rightNode == null){
                        rightNode = new TreeNode(Integer.parseInt(valArr[2]));
                        node.right = rightNode;
                        lookupMap.put(Integer.parseInt(valArr[2]),rightNode);
                    }
                }
            }
            return root;
        }
    }
}
