package com.wsz.test.algorithm.graph;

import java.util.*;

public class GraphTest {
    static class Graph {
        // 点
        Map<Integer, Node> nodeMap;
        // 边
        Set<Side> sideSet;
    }

    static class Node {
        // 点值
        int value;
        // 入边数
        int in;
        // 出边数
        int out;
        // 出边点集合
        List<Node> nextList;
        // 出边集合
        List<Side> sides;
    }

    static class Side {
        // 边的权重
        int weight;
        // 起边
        Node from;
        // 终边
        Node to;
    }
    // 图的宽度遍历
    static void bfs1(Node graph){
        Queue<Node> queue = new LinkedList<>();
        Set<Node> nodes = new HashSet<>();
        queue.add(graph);
        nodes.add(graph);
        while (!queue.isEmpty()){
            Node n = queue.poll();
            System.out.println(n.value);
            if(n.nextList != null && n.nextList.size() > 0){
                for(Node node : n.nextList){
                    if(!nodes.contains(node)){
                        nodes.add(node);
                        queue.add(node);
                    }
                }
            }

        }
    }
    static void bfs2(Node graph){
        Stack<Node> stack = new Stack<>();
        Set<Node> nodeSet = new HashSet<>();
        stack.push(graph);
        nodeSet.add(graph);
        System.out.println(graph.value);
        if(!stack.isEmpty()){
            Node curr = stack.pop();
            if(curr.nextList != null && curr.nextList.size() > 0){
                for(Node node : curr.nextList){
                    if(!nodeSet.contains(node)){
                        nodeSet.add(node);
                        System.out.println(node.value);
                        stack.push(curr);
                        stack.push(node);
                        break;
                    }
                }
            }
        }
    }
}
