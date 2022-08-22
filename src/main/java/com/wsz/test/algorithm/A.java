package com.wsz.test.algorithm;

public class A {


    public static void main(String[] args) {
       System.out.println(1<<2);
    }
    public static void printLastCharLength(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        String[] strArr = str.split(" ");
        System.out.println(strArr[strArr.length-1].length());

    }

    public static void printPatternStr(String aStr, String bStr) {

        char[] aCharArr = aStr.toCharArray();
        char[] bCharArr = bStr.toCharArray();
        int[] nextArr = getNext(bCharArr);
        int count =0;
        int x =0;
        int y = 0;
        while(x < aCharArr.length){
            while(x < aCharArr.length && y < bCharArr.length){
                if(matchChar(aCharArr[x], bCharArr[y])){
                    x++;
                    y++;
                }else if(nextArr[y] == -1){
                    x++;
                }else{
                    y = nextArr[y];
                }
            }
            if(y == bCharArr.length){
                count++;
                y=0;
            }
        }
        System.out.println(count);
    }
    public static int[] getNext(char[] charArr){
        if(charArr == null || charArr.length == 0){
            return null;
        }
        int[] nextArr = new int[charArr.length];
        if(charArr.length == 1){
            nextArr[0] = -1;
            return nextArr;
        }
        if(charArr.length == 2){
            nextArr[0] = -1;
            nextArr[1] = 0;
            return nextArr;
        }
        nextArr[0] = -1;
        nextArr[1] = 0;
        int cn = 0;
        int i = 2;
        while(i < charArr.length){
            if(matchChar(charArr[i - 1],charArr[cn])){
                nextArr[i++] = nextArr[++cn];
            }else if(cn > 0){
                cn = nextArr[cn];
            }else{
                nextArr[i++] = 0;
            }
        }
        return nextArr;
    }
    public static boolean matchChar(char a ,char b){
        if(a == b){
            return true;
        }
        if(a < 65 || a> 122 || b < 65 || b > 122){
            return false;
        }
        int abs = Math.abs((int)a - (int)b);
        if(abs == 32){
            return true;
        }
        return false;
    }


}
