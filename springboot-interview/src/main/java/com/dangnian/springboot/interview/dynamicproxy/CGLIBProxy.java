package com.dangnian.springboot.interview.dynamicproxy;


import org.aopalliance.intercept.MethodInvocation;
import org.omg.IOP.ENCODING_CDR_ENCAPS;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLIBProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理前");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("代理后");
        return result;
    }


    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OperaterImpl.class);
        enhancer.setCallback(new CGLIBProxy());
        OperaterImpl operater = (OperaterImpl) enhancer.create();
        operater.query(1);
    }


}
