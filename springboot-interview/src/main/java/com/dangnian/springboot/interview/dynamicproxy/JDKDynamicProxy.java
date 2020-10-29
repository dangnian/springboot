package com.dangnian.springboot.interview.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxy implements InvocationHandler {

    private IOperater iOperater;

    public JDKDynamicProxy(IOperater iOperater) {
        this.iOperater = iOperater;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前");
        Object result = method.invoke(iOperater, args);
        System.out.println("代理后");
        return result;
    }


    public static void main(String[] args) {
        IOperater proxyInstance = (IOperater) Proxy.newProxyInstance(IOperater.class.getClassLoader(), new Class[]{IOperater.class}, new JDKDynamicProxy(new OperaterImpl()));
        proxyInstance.insert(1);
    }
}
