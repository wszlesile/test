package com.wsz.test.algorithm;

public class D {
    public static void main(String[] args) {
        String a = "0x2C";
        String replace = a.replace("0x", "");
        char[] cArr = replace.toCharArray();
        int sum = 0;

        for(int i = cArr.length -1;i>=0;i--){
            int temp = (int) Math.pow(16,cArr.length-1-i);
            int t = cArr[i];
            if(65 <= t && t <= 70){
                t = 10 + (t-65);
            }else {
                t = 1 + (t-49);
            }
            sum+=temp*t;

        }
        int sc = 16;
        int n = 16;
        System.out.println(sc = n - (n >>> 2));
    }
}
