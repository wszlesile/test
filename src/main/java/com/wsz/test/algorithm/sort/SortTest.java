package com.wsz.test.algorithm.sort;

public class SortTest {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 9, 2, 1, 8, 7};
        insertSort(arr);
        System.out.println(arr);
    }

    public static void swap(int[] arr, int aIndex, int bIndex) {
        int temp = arr[aIndex];
        arr[aIndex] = arr[bIndex];
        arr[bIndex] = temp;
    }

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
    // 插入排序
    public static void insertSort(int[] arr){
        for(int i = 1;i<arr.length;i++){
            for(int j = i-1; j >=0 && arr[j+1] < arr[j];j--){
                 swap(arr,j+1,j);
            }
        }
    }
    // 选择排序
    public static void selectSort(int[] arr){
        for(int i = 0;i < arr.length-1;i++){
            int minIndex = i;
            for(int j = i+1;j< arr.length;j++){
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            swap(arr,i,minIndex);
        }
    }
    // 荷兰国旗问题 partition
    public static int[] neitherLandsFlag(int[] arr,int L,int R){
        int less = L -1;
        int more = R;
        int index = L;
        while (index < more){
           if(arr[index] < arr[R]){
               swap(arr,index++,++less);
           }else if(arr[index] > arr[R]){
               swap(arr,index,--more);
           }else{
               index++;
           }
        }
        swap(arr,R,more);
        int[] result = new int[2];
        result[0] = less+1;
        result[1] = more;
        return result;
    }
    // 快速排序
    public static void quickSort(int[] arr,int startIndex,int endIndex){
        if(startIndex == endIndex){
            return;
        }
        // 低位索引
        int i = startIndex+1;
        // 高位索引
        int j = endIndex;
        // 此循环的作用是保证startIndex的左边都比startIndex的值小 右边都大于等于startIndex的值
        while (true){
            // 找到第一个比startIndex处的值大的
            while (i+1<=endIndex && arr[i] <= arr[startIndex]){
                i++;
            }
            while(j-1 >= startIndex && arr[j] >= arr[startIndex]){
                j--;
            }
            if(i >= j){
                break;
            }
           swap(arr,i,j);
        }
        swap(arr,j,startIndex);
        if(j-1>startIndex){
            quickSort(arr,startIndex,j-1);
        }
        if(i<endIndex){
            quickSort(arr,i,endIndex);
        }
    }
    // 归并排序
    public static void mergeSort(int[] arr,int lIndex,int rIndex){
      if(lIndex == rIndex){
          // base case 一个元素已经是有序状态
          return;
      }
      // 算出中间节点的索引
      int midIndex = lIndex + ((rIndex-lIndex)>>1);
      mergeSort(arr,lIndex,midIndex);
      mergeSort(arr,midIndex+1,rIndex);
      mergeData(arr,lIndex,midIndex,rIndex);
    }
    private static void mergeData(int[] arr,int lIndex,int midIndex,int rIndex){
        // 辅助数组
        int[] hepArr = new int[rIndex-lIndex+1];
        // 两个指针
        int lp = lIndex;
        int rp = midIndex+1;
        int curr = 0;
        while (lp <= midIndex && rp <= rIndex) {
            if(arr[lp] <= arr[rp]){
                hepArr[curr++] = arr[lp++];
            }else{
                hepArr[curr++] = arr[rp++];
            }
        }
        while (lp <= midIndex){
            hepArr[curr++] = arr[lp++];
        }
        while (rp <= rIndex){
            hepArr[curr++] = arr[rp++];
        }
        for(int i = 0;i<hepArr.length;i++){
            arr[i+lIndex] = hepArr[i];
        }
    }
    // 堆排序
    public static void heapSort(int[] arr){
      int heapSize = arr.length;
      for(int i = arr.length-1;i>=0;i--){
          heapify(arr,i,heapSize);
      }
      swap(arr,0,--heapSize);
      while (heapSize > 0){
          heapify(arr,0,heapSize);
          swap(arr,0,--heapSize);
      }
    }
    public static void heapify(int[] arr,int index,int heapSize){
        int lIndex = (index<<1)+1;
        while (lIndex <=heapSize-1){
            int rIndex = lIndex+1;
            int maxIndex  = (rIndex <= heapSize-1)?(arr[rIndex] > arr[lIndex] ? rIndex : lIndex):lIndex;
            if(arr[maxIndex] < arr[index]){
                maxIndex = index;
            }
            if(maxIndex == index){
                break;
            }
            swap(arr,index,maxIndex);
            index = maxIndex;
            lIndex = (index<<1)+1;
        }
    }
}
