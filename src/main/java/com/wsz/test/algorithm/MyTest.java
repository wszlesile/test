package com.wsz.test.algorithm;

import java.util.*;

public class MyTest {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                4, 3, 1, 2, 10, 13, 11, 5
        };
        //quickSort(arr,0,arr.length-1);
        //bubbleSort(arr);
        //insertSort(arr);
        //selectSort(arr);
        //mergeSort(arr,0,arr.length-1);
        //heapSort(arr);
        System.out.println(matchKMP("abbbbaaaa", "ba"));
    }

    // 交换两个数
    private static void swap(Integer[] arr, int i, int j) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 冒泡排序 O(N2)
    private static void bubbleSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // i表示进行多少躺冒泡（比如说n个数，只有执行n-1躺就可以完全排好序）
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // 插入排序 O(N2)
    private static void insertSort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // 选择排序
    private static void selectSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    // 快速排序O(N*(logN))
    private static void quickSort(Integer[] arr, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return;
        }
        int i = startIndex + 1;
        int j = endIndex;
        while (true) {
            while (i < endIndex && arr[i] <= arr[startIndex]) {
                i++;
            }
            while (j > startIndex && arr[j] >= arr[startIndex]) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, j, startIndex);
        if (j > startIndex) {
            quickSort(arr, startIndex, j - 1);
        }
        if (j < endIndex) {
            quickSort(arr, j + 1, endIndex);
        }
    }

    // 归并排序
    private static void mergeSort(Integer[] arr, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return;
        }
        int mid = startIndex + ((endIndex - startIndex) >> 1);
        mergeSort(arr, startIndex, mid);
        mergeSort(arr, mid + 1, endIndex);
        mergeData(arr, startIndex, mid, endIndex);
    }

    private static void mergeData(Integer[] arr, int lIndex, int midIndex, int rIndex) {
        Integer[] helpArr = new Integer[rIndex - lIndex + 1];
        int lp = lIndex;
        int rp = midIndex + 1;
        int hIndex = 0;
        while (lp <= midIndex && rp <= rIndex) {
            if (arr[lp] <= arr[rp]) {
                helpArr[hIndex++] = arr[lp++];
            } else {
                helpArr[hIndex++] = arr[rp++];
            }
        }
        while (lp <= midIndex) {
            helpArr[hIndex++] = arr[lp++];
        }
        while (rp <= rIndex) {
            helpArr[hIndex++] = arr[rp++];
        }
        for (int j = 0; j < helpArr.length; j++) {
            arr[lIndex + j] = helpArr[j];
        }
    }

    // 堆排序
    private static void heapSort(Integer[] arr) {
        int heapSize = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(Integer[] arr, int index, int heapSize) {
        int lIndex = 2 * index + 1;
        while (lIndex < heapSize) {
            int rIndex = lIndex + 1;
            int maxIndex = (rIndex <= heapSize - 1) ? (arr[lIndex] < arr[rIndex] ? rIndex : lIndex) : lIndex;
            if (arr[maxIndex] < arr[index]) {
                maxIndex = index;
            }
            if (maxIndex == index) {
                break;
            }
            swap(arr, maxIndex, index);
            index = maxIndex;
            lIndex = 2 * index + 1;
        }
    }

    /**
     * 二叉树相关算法
     **/
    // 二叉树节点信息node
    private static class MyNode {
        int val;
        MyNode leftNext;
        MyNode rightNext;
        MyNode parentNode;
    }

    // 深度遍历 递归写法
    private static void recursionPrint(MyNode node) {
        if (node == null) {
            // 为空不打印
            return;
        }
        // 此处打印 为先序
        if (node.leftNext != null) {
            recursionPrint(node.leftNext);
        }
        // 此处打印为中序
        if (node.rightNext != null) {
            recursionPrint(node.rightNext);
        }
        // 此处打印为后序
    }

    // 深度遍历 利用栈结构 先序
    private static void prePrint(MyNode h) {
        Stack<MyNode> stack = new Stack<>();
        stack.push(h);
        while (!stack.empty()) {
            MyNode node = stack.pop();
            // 打印
            System.out.println(node.val);
            // 判断打印节点是否有左右孩子节点
            if (node.rightNext != null) {
                stack.push(node.rightNext);
            }
            if (node.leftNext != null) {
                stack.push(node.leftNext);
            }
        }
    }

    // 深度遍历 利用栈结构 中序
    private static void midPrint(MyNode h) {
        Stack<MyNode> stack = new Stack<>();
        while (!stack.empty() || h != null) {
            if (h != null) {
                stack.push(h);
                h = h.leftNext;
            } else {
                h = stack.pop();
                System.out.println(h.val);
                h = h.rightNext;
            }
        }
    }

    // 深度遍历 利用栈结构 后序
    private static void posPrint(MyNode h) {
        Stack<MyNode> stack = new Stack<>();
        stack.push(h);
        MyNode c = null;
        while (!stack.empty()) {
            c = stack.peek();
            if (c.leftNext != null && c.leftNext != h && c.rightNext != h) {
                stack.push(c.leftNext);
            } else if (c.rightNext != null && c.rightNext != h) {
                stack.push(c.rightNext);
            } else {
                c = stack.pop();
                System.out.println(c.val);
                h = c;
            }
        }
    }

    // 二叉树的按层遍历 使用队列
    private static void levelPrint(MyNode h) {
        Queue<MyNode> queue = new LinkedList<>();
        queue.add(h);
        while (!queue.isEmpty()) {
            h = queue.poll();
            System.out.println(h.val);
            // 打印的同时 下一层节点入队列
            if (h.leftNext != null) {
                queue.add(h.leftNext);
            }
            if (h.rightNext != null) {
                queue.add(h.rightNext);
            }

        }
    }

    // 打印二叉树的最大宽度 使用map方法
    private static int getMaxLevel(MyNode h) {
        if (h == null) {
            return 0;
        }
        Queue<MyNode> queue = new LinkedList<>();
        queue.add(h);
        Map<MyNode, Integer> levelMap = new HashMap<>();
        levelMap.put(h, 1);
        // 当前处理层
        int currLevel = 1;
        // 当前处理层的节点数
        int currLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            MyNode curr = queue.poll();
            int tempLevel = levelMap.get(curr);
            System.out.println(curr.val);
            if (curr.leftNext != null) {
                queue.add(curr.leftNext);
                levelMap.put(curr.leftNext, currLevel + 1);
            }
            if (curr.rightNext != null) {
                queue.add(curr.rightNext);
                levelMap.put(curr.rightNext, currLevel + 1);
            }
            if (tempLevel == currLevel) {
                // 还在处理当前层
                currLevelNodes++;
            } else {
                // 结算上一层的宽度
                max = Math.max(max, currLevelNodes);
                // 处理层数+1
                currLevel++;
                currLevelNodes = 1;

            }
        }
        return Math.max(max, currLevelNodes);
    }

    // 获取二叉树宽度 不使用map的方法
    private static int getMaxLevelWithOutMap(MyNode h) {
        if (h == null) {
            return 0;
        }
        Queue<MyNode> queue = new LinkedList<>();
        queue.add(h);
        MyNode currEnd = h;
        MyNode nextEnd = null;
        int max = 0;
        int currLevelNodes = 0;
        while (!queue.isEmpty()) {
            MyNode currNode = queue.poll();
            if (currNode.leftNext != null) {
                queue.add(currNode.leftNext);
                nextEnd = currNode.leftNext;
            }
            if (currNode.rightNext != null) {
                queue.add(currNode.rightNext);
                nextEnd = currNode.rightNext;
            }
            currLevelNodes++;
            if (currNode == currEnd) {
                max = Math.max(max, currLevelNodes);
                currLevelNodes = 0;
                currEnd = nextEnd;
            }
        }
        return max;
    }

    // 判断一个二叉树是不是平衡的
    static class CalculateInfo {
        // 是否平衡
        boolean isBalance = false;
        // 高度
        int highLength;

        public CalculateInfo(boolean isBalance, int highLength) {
            this.isBalance = isBalance;
            this.highLength = highLength;
        }
    }

    private static CalculateInfo calculateNode(MyNode node) {
        // base case
        if (node == null) {
            return new CalculateInfo(true, 0);
        }
        // 左子树判断信息
        CalculateInfo leftInfo = calculateNode(node.leftNext);
        // 右子树判断信息
        CalculateInfo rightInfo = calculateNode(node.rightNext);
        int highLength = Math.max(leftInfo.highLength, rightInfo.highLength) + 1;
        boolean isBalance = false;
        if (leftInfo.isBalance && rightInfo.isBalance && highLength < 2) {
            isBalance = true;
        }
        return new CalculateInfo(isBalance, highLength);
    }

    // 判断一个二叉树是不是满二叉树
    static class FullTreeInfo {
        // 是不是满二叉树
        boolean isFull;
        // 是不是完全二叉树
        boolean isCb;
        // 高度
        int highLength;

        public FullTreeInfo(boolean isFull, boolean isCb, int highLength) {
            this.isFull = isFull;
            this.isCb = isCb;
            this.highLength = highLength;
        }
    }

    private static FullTreeInfo getFullTreeInfo(MyNode node) {
        if (node == null) {
            return new FullTreeInfo(true, true, 0);
        }
        FullTreeInfo leftInfo = getFullTreeInfo(node);
        FullTreeInfo rightInfo = getFullTreeInfo(node);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.highLength == rightInfo.highLength;
        int highLength = leftInfo.highLength + rightInfo.highLength + 1;
        boolean isCb = false;
        if (isFull) {
            isCb = true;
        } else if (leftInfo.isCb && rightInfo.isCb) {
            if (leftInfo.isFull && leftInfo.highLength - rightInfo.highLength <= 1) {
                isCb = true;
            } else if (leftInfo.isCb && rightInfo.isFull && leftInfo.highLength == rightInfo.highLength + 1) {
                isCb = true;
            }
        }
        return new FullTreeInfo(isFull, isCb, highLength);
    }

    // 获取二叉树的最大二叉搜索树
    static class SearchInfo {
        boolean isAllStNodes;
        int stNodeSize;
        int max;
        int min;
        int highLength;
    }

    private static SearchInfo getSearchInfo(MyNode node) {
        if (node == null) {
            return null;
        }
        SearchInfo leftInfo = getSearchInfo(node.leftNext);
        SearchInfo rightInfo = getSearchInfo(node.leftNext);
        int max = leftInfo == null ? node.val : Math.max(leftInfo.max, node.val);
        int min = rightInfo == null ? node.val : Math.max(rightInfo.min, node.val);
        boolean isAllStNodes;
        int stNodeSize;
        int highLength;
        return null;
    }

    // 获取二叉树的最大节点距离
    static class MaxDistanceInfo {
        // 高度
        int highLength;
        // 距离
        int maxDistance;

        public MaxDistanceInfo(int highLength, int maxDistance) {
            this.highLength = highLength;
            this.maxDistance = maxDistance;
        }
    }

    private static MaxDistanceInfo getMaxDistanceInfo(MyNode node) {
        if (node == null) {
            return new MaxDistanceInfo(0, 0);
        }
        MaxDistanceInfo leftInfo = getMaxDistanceInfo(node.leftNext);
        MaxDistanceInfo rightInfo = getMaxDistanceInfo(node.rightNext);
        int highLength = Math.max(leftInfo.highLength, rightInfo.highLength) + +1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.highLength + rightInfo.highLength + 1);
        return new MaxDistanceInfo(highLength, maxDistance);
    }

    // 给定一个二叉树的某个节点 返回其后继节点
    private static MyNode getSuccessorNode(MyNode node) {
        // case1 先判断该节点有没有右子树 有的话 后继节点则为该子树的最左孩子
        if (node.rightNext != null) {
            return getLastLeftNode(node.rightNext);
        }
        // case2 判断节点是不是父节点的左孩子 不是 继续找父节点 再判断
        MyNode parentNode = node.parentNode;
        while (parentNode != null && node != parentNode.leftNext) {
            node = parentNode;
            parentNode = parentNode.parentNode;
        }
        return parentNode;
    }

    private static MyNode getLastLeftNode(MyNode node) {
        if (node == null) {
            return null;
        }
        while (node.leftNext != null) {
            node = node.leftNext;
        }
        return node;
    }

    // 返回一个二叉树 n1和n2的最低祖宗节点 使用map和set
    private static MyNode getCommonParentNode1(MyNode head, MyNode h1, MyNode h2) {
        Map<MyNode, MyNode> parentMap = new HashMap<>(16);
        fillParentNode(head, parentMap);
        Set<MyNode> h1ParentNodes = new HashSet<>();
        MyNode curr = h1;
        while (parentMap.get(curr) != null) {
            h1ParentNodes.add(parentMap.get(curr));
            curr = parentMap.get(curr);
        }
        h1ParentNodes.add(h1);
        curr = h2;
        while (!h1ParentNodes.contains(curr)) {
            curr = parentMap.get(curr);
        }
        return curr;
    }

    private static void fillParentNode(MyNode parentNode, Map<MyNode, MyNode> parentMap) {
        if (parentNode == null) {
            return;
        }
        if (parentNode.leftNext != null) {
            parentMap.put(parentNode.leftNext, parentNode);
            fillParentNode(parentNode.leftNext, parentMap);
        }
        if (parentNode.rightNext != null) {
            parentMap.put(parentNode.rightNext, parentNode);
            fillParentNode(parentNode.rightNext, parentMap);
        }
    }

    // 返回一个二叉树 n1和n2的最低祖宗节点 不使用map
    static class ParentInfo {
        boolean findH1;
        boolean findH2;
        MyNode commonParentNode;

        public ParentInfo(boolean findH1, boolean findH2, MyNode commonParentNode) {
            this.findH1 = findH1;
            this.findH2 = findH2;
            this.commonParentNode = commonParentNode;
        }
    }

    private static ParentInfo getCommonParentNode2(MyNode node, MyNode h1, MyNode h2) {
        if (node == null) {
            return new ParentInfo(false, false, null);
        }
        ParentInfo leftParentInfo = getCommonParentNode2(node.leftNext, h1, h2);
        ParentInfo rightParentInfo = getCommonParentNode2(node.rightNext, h1, h2);
        boolean findH1 = node == h1 || leftParentInfo.findH1 || rightParentInfo.findH1;
        boolean findH2 = node == h2 || leftParentInfo.findH2 || rightParentInfo.findH2;
        MyNode commonParentNode = null;
        if (leftParentInfo.commonParentNode != null) {
            commonParentNode = leftParentInfo.commonParentNode;
        }
        if (rightParentInfo.commonParentNode != null) {
            commonParentNode = rightParentInfo.commonParentNode;
        }
        if (commonParentNode == null) {
            if (findH1 && findH2) {
                commonParentNode = node;
            }
        }
        return new ParentInfo(findH1, findH2, commonParentNode);

    }

    // 模式串匹配 "abb" "hheeee"
    private static boolean mathPatternStr(String pattern, String value) {
        if (pattern == null || value == null) {
            return false;
        }
        if ("".equals(pattern)) return "".equals(value);
        if ("".equals(value)) return pattern.length() < 2;
        char[] patternCharArr = pattern.toCharArray();
        char[] valueCharArr = value.toCharArray();
        int aCount = 0;
        int bCount = 0;
        for (char c : patternCharArr) {
            if (c == 'a') {
                aCount++;
            } else {
                bCount++;
            }
        }
        if (aCount == 0) {
            // 单字符模式
            return singlePatternMath(valueCharArr, bCount);
        } else if (bCount == 0) {
            // 单字符模式
            return singlePatternMath(valueCharArr, aCount);
        }
        return aCount < bCount ? mutiMatch(valueCharArr, patternCharArr, aCount, bCount, 'a') : mutiMatch(valueCharArr, patternCharArr, aCount, bCount, 'b');
    }

    private static boolean mutiMatch(char[] valueCharArr, char[] patternCharArr, int smallCount, int bigCount, char smallChar) {
        int smallSumCount = 0;
        search:
        while ((smallSumCount += smallCount) < valueCharArr.length) {
            int remain = valueCharArr.length - smallSumCount;
            if (remain % bigCount != 0) {
                continue;
            }
            int bigLen = remain / bigCount;
            int smallLen = smallSumCount / smallCount;
            String baseSmallValue = null;
            String baseBigValue = null;
            int stepIndex = 0;
            for (char c : patternCharArr) {
                if (c == smallChar) {
                    // 构造small字符base字符串
                    if (baseSmallValue == null) {
                        baseSmallValue = new String(valueCharArr, stepIndex, smallLen);
                    } else if (!checkPattern(valueCharArr, baseSmallValue, stepIndex)) {
                        continue search;
                    }
                    stepIndex += smallLen;
                } else {
                    // 构造big字符base字符串
                    if (baseBigValue == null) {
                        baseBigValue = new String(valueCharArr, stepIndex, bigLen);
                    } else if (!checkPattern(valueCharArr, baseBigValue, stepIndex)) {
                        continue search;
                    }
                    stepIndex += bigLen;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean singlePatternMath(char[] valueCharArr, int matchPatternCount) {
        if (valueCharArr.length % matchPatternCount != 0) {
            return false;
        }
        String baseValue = new String(valueCharArr, 0, valueCharArr.length / matchPatternCount);
        int stepIndex = 0;
        while (stepIndex < valueCharArr.length) {
            if (!checkPattern(valueCharArr, baseValue, stepIndex)) {
                return false;
            }
            stepIndex += baseValue.length();
        }
        return true;
    }

    private static boolean checkPattern(char[] valueCharArr, String baseValue, int startMathIndex) {
        int valueIndex = 0;
        for (char c : baseValue.toCharArray()) {
            if (c != valueCharArr[startMathIndex + (valueIndex++)]) {
                return false;
            }
        }
        return true;
    }

    // KMP算法
    private static int matchKMP(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = getNextArr(str2);
        // x y 分别为两个字符串字符比较的索引位置
        int x = 0, y = 0;
        // 终止条件为 索引位置越界
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    private static int[] getNextArr(char[] strArr) {
        if (strArr == null) {
            return null;
        }
        int[] next = new int[strArr.length];
        if (strArr.length == 1) {
            next = new int[1];
            next[0] = -1;
            return next;
        }
        next[0] = -1;
        next[1] = 0;
        if (next.length == 2) {
            return next;
        }
        // i-1 即前一个位置的next值
        int preNext = 0;
        int i = 2;
        while (i < next.length) {
            if (strArr[i - 1] == strArr[preNext]) {
                // KMP算法的核心 如果前一个字符和前一个字符的next索引处的字符相等 则当前字符的next值为前一个字符next+1
                next[i++] = ++preNext;
            } else if (preNext > 0) {
                preNext = next[preNext];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


    // 点灯 贪心算法
    public static int getDianDengNums(char[] str) {
        int index = 0;
        int lightNums = 0;
        while (index < str.length) {
            if (str[index] == 'X') {
                index++;
            } else {
                lightNums++;
                if ((index + 1) == str.length) {
                    break;
                } else if (str[index + 1] == 'X') {
                    index = index + 2;
                } else {
                    index = index + 3;
                }

            }
        }
        return lightNums;
    }
}

