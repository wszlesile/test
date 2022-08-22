package com.wsz.test.algorithm;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class E {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String val = in.nextLine();
            char[] strArr = val.toCharArray();

            Integer iVal=Integer.parseInt(val);
            int count = 0;
            for(int i = 31;i>=1;i--){
                int temp = ((int)Math.pow(2,i-1)) & iVal;
                count+=((temp != 0) ? 1 : 0);
            }
            System.out.print(count);
        }
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("1","1");

    }
}
