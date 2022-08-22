package com.wsz.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class H {
    public static void main(String[] args) {
        TreeNode r2l3 = new TreeNode(2);
        TreeNode r3 = new TreeNode(4,r2l3,null);

        TreeNode r2 = new TreeNode(1,null,null);
       TreeNode root = new TreeNode(3,r2,r3);
        recoverTree(root);
    }
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
}
