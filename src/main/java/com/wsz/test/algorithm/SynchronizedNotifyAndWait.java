package com.wsz.test.algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedNotifyAndWait {
    static final Object o = new Object();
    final int a  = 1;
    final int b = a;
    public static void main(String[] args) {
        String[] aT = new String[]{"A","bean.B","C","D","bean.E","F","G"};
        String[] bT = new String[]{"1","2","3","4","5","6","7"};

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(()->{
            return 1;
        });
        String s = String.format("\nif (arg%d == null) throw new IllegalArgumentException(\"invocation == null\");", 0);

    }
}
