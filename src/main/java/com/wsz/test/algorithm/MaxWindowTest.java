package com.wsz.test.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MaxWindowTest {
    public static void main(String[] args) {
        Map map  = new ConcurrentHashMap<>();
        map.put(1,1);
        map = new HashMap();
        map.put(1,1);
      int[] arr = new int[]{3,2,4,5,7,9};
      System.out.println(getMaxWindow(arr,3));
    }

    // 数组arr 窗口大小w
    public static int[] getMaxWindow(int[] arr, int w) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        int[] res = new int[arr.length-w+1];
        int resIndex = 0;
        for(int i = 0;i<arr.length;i++){
             while (!linkedList.isEmpty() && linkedList.peekLast() <= arr[i]){
                 linkedList.pollLast();
             }
             linkedList.addLast(i);
             if(linkedList.peekFirst() == i - w){
                 linkedList.pollFirst();
             }
             if(i >=w-1){
                 res[resIndex++] = arr[linkedList.peekFirst()];
             }
        }
        return res;
    }
}
