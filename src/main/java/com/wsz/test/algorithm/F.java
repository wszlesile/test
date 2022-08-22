package com.wsz.test.algorithm;

import java.util.*;

public class F {
    public static void main(String[] args) {
        int num = 180;
        // 小于 180的质数集合 从小到大
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(11);
        // 存储结果
        PriorityQueue<Integer> priorityQueue= new PriorityQueue<>();
        int currFactorIndex = 0;
        int result = 1;
        int remainNum = num;
        while (remainNum > 1){
            if(currFactorIndex <= list.size() -1){
                int currFactorNum = list.get(currFactorIndex);
                if (remainNum % currFactorNum == 0){
                    priorityQueue.add(currFactorNum);
                    result =  result * currFactorNum;
                    remainNum = num / (result);
                }else{
                    currFactorIndex++;
                }
            }else{
                // 有质数没有被预热
                list.add(getNextFactor(list.get(currFactorIndex-1),list));
            }
        }
        if(result == num){
            while (priorityQueue.peek() != null){
                System.out.println(priorityQueue.poll());
            }
        }else{
            System.out.println(num);
        }
    }

    public static Integer getNextFactor(Integer num,List<Integer> list){
        Integer nextNum = num+1;
        int currIndex = 0;
        // 判断是否为素数
        boolean flag = true;
        while (currIndex <= list.size()-1){
            if(nextNum % list.get(currIndex) == 0){
                flag = false;
                break;
            }
            currIndex++;
        }
        if(flag){
            return nextNum;
        }
        return getNextFactor(nextNum,list);
    }
}
