package com.wsz.test.algorithm;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String a = in.nextLine();
            printLastCharLength(a);
        }
    }
    public static void printLastCharLength(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        String[] strArr = str.split(" ");
        System.out.println(strArr[strArr.length-1].length());
    }
}
