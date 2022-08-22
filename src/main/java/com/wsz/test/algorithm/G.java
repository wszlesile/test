package com.wsz.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class G {
    static class TreeNode {
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
    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode hasProcessedNode = null;
        while (!stack.empty()) {
            root = stack.peek();
            if (root.left != null && root.left != hasProcessedNode && (hasProcessedNode == null || (root.right != hasProcessedNode))) {
                stack.push(root.left);
            } else if (root.right != null && (hasProcessedNode == null || root.right != hasProcessedNode)) {
                stack.push(root.right);
            } else {
                hasProcessedNode = stack.pop();
                list.add(hasProcessedNode.val);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode r2l3 = new TreeNode(3);
        TreeNode r2 = new TreeNode(2,r2l3,null);
        TreeNode root = new TreeNode(1,null,r2);

        postorderTraversal(root);
    }
}
