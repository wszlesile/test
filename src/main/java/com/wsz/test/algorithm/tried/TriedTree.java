package com.wsz.test.algorithm.tried;

public class TriedTree {
    public static void main(String[] args) {
       String a = " a 91b";
        char[] chars = a.toCharArray();
        StringBuilder subB = new StringBuilder();
       char b = '1';
       String c = String.valueOf(b);
        subB.append(b);

        String[] ar = a.split(" ");
       System.out.println(ar.length);
    }

    // 前缀树节点
    static class TriedNode {
        // 节点pass值 某个字符经过的个数
        int pass;
        // 节点end值 某个字符结尾的个数
        int end;
        TriedNode[] nextNodes = new TriedNode[26];
    }

    // 前缀树根节点
    TriedNode root;

    // 字符串加入前缀树
    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] str = word.toCharArray();
        TriedNode node = this.root;
        node.pass++;
        for(char c : str){
            // 计算出字符的next路径索引
            int index = c - 'a';
            TriedNode next = node.nextNodes[index];
            if(next == null){
                next = new TriedNode();
            }
            node = next;
            node.pass++;
        }
        node.end++;
    }
    // 查询字符串被加入过几次
    public int search(String word){
        if(word == null){
            return 0;
        }
        char[] str = word.toCharArray();
        TriedNode node = this.root;
        for(char c : str){
            int index = c - 'a';
            if(node.nextNodes[index] == null){
                return 0;
            }
            node = node.nextNodes[index];
        }
        return node.end;
    }

    // 查询以prefix为前缀的字符串有多少个
    public int prefix(String word){
        if(word == null){
            return 0;
        }
        TriedNode node = this.root;
        char[] str = word.toCharArray();
        for(char c : str){
            int index = c - 'a';
            if(node.nextNodes[index] == null){
                return 0;
            }
            node = node.nextNodes[index];
        }
        return node.pass;
    }
    // 在前缀树中删除字符串word
    public void delete(String word){
        if(word == null){
            return;
        }
        if(this.search(word) == 0){
            // 前缀树木中不存在 直接返回
            return;
        }
        TriedNode node = this.root;
        node.pass--;
        char[] str = word.toCharArray();
        for(char c : str){
            int index = c - 'a';
            if(--node.nextNodes[index].pass == 0){
                node.nextNodes[index] = null;
                return;
            }
            node = node.nextNodes[index];
        }
        node.end--;
    }
}
