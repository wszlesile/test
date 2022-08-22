package com.wsz.test.algorithm.union;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// 并查集
public class UnionSet<V> {
    static class Node<V>{
        // 集合元素
        V value;
        // 父节点 集合标记点
        Node<V> parent;
    }
    UnionSet(List<V> valList){
        for(V val : valList){
            Node<V> node = new Node<>();
            nodeMap.put(val,node);
            parentMap.put(node,node);
            sizeMap.put(node,1);
        }
    }
    Map<V,Node<V>> nodeMap = new HashMap<>(16);
    Map<Node<V>,Node<V>> parentMap = new HashMap<>(16);
    Map<Node<V>,Integer> sizeMap = new HashMap<>(16);
    private Node<V> findParentNode(Node<V> node){
        if(!parentMap.containsKey(node)){
            return null;
        }
        // 找最顶端的父节点 即集合标记节点
        Node<V> curr = node;
        Stack<Node<V>> stack = new Stack<>();
        while (curr != parentMap.get(node)){
            stack.push(curr);
            curr = parentMap.get(node);
        }
        // 优化链路
        if(!stack.isEmpty()){
            parentMap.put(stack.pop(),curr);
        }
        return curr;
    }
    public boolean isSameSet(V a,V b){
        if(!nodeMap.containsKey(a) || !nodeMap.containsKey(b)){
            // 不属于整个并查集集合 直接返回false
            return false;
        }
        Node<V> aHead = findParentNode(nodeMap.get(a));
        Node<V> bHead = findParentNode(nodeMap.get(b));
        return aHead == bHead ? true : false;
    }

    public void unionSet(V a,V b){
        if(!nodeMap.containsKey(a) || !nodeMap.containsKey(b)){
            // 不属于整个并查集集合 直接返回
            return;
        }
        Node<V> aHead = parentMap.get(nodeMap.get(a));
        Node<V> bHead = parentMap.get(nodeMap.get(b));
        Integer aSize;
        Integer bSize;
        if((aSize = sizeMap.get(aHead)) >= (bSize = sizeMap.get(bHead))){
             parentMap.put(bHead,aHead);
             sizeMap.put(aHead,aSize+bSize);
             sizeMap.remove(bHead);
        }else{
            parentMap.put(aHead,bHead);
            sizeMap.put(bHead,aSize+bSize);
            sizeMap.remove(aHead);
        }
    }

}
