package com.wsz.test.algorithm.poxy;

public class TargetObj implements ITargetObj{
    @Override
    public void targetMethod() {
        System.out.println("targetMethod invoke");
    }
}
