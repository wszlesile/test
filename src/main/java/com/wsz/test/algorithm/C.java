package com.wsz.test.algorithm;

import java.util.*;

public class C {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int numCount = 0;
        PriorityQueue<Integer> set = new PriorityQueue<>();
        boolean getNumCount = false;
        int count = 0;
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String  str = in.nextLine();
           
            Integer val = Integer.valueOf(str);
            if(!getNumCount){
                numCount = val;
                getNumCount = true;
            }else{
                count++;
                if(!set.contains(val)){
                    set.add(val);
                }
            }
            if(numCount == count){
                while (set.peek() != null){
                    System.out.println(set.poll());
                }
                getNumCount = false;
                numCount = 0;
                count = 0;
            }
        }
    }
}
