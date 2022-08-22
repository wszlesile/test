package com.wsz.test.algorithm.poxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
    static Object getProxy(final ITargetObj targetObj){
       return Proxy.newProxyInstance(ITargetObj.class.getClassLoader(), new Class[]{ITargetObj.class}, new InvocationHandler() {
           ITargetObj target = targetObj;
           @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               System.out.println("before");
                target.targetMethod();
                System.out.println("before");
                return null;
            }
        });
    }

    public static void main(String[] args) {
        ITargetObj targetObj = new TargetObj();
        ITargetObj obj = (ITargetObj) getProxy(targetObj);
        obj.targetMethod();

    }
}
