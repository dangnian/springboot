package com.dangnian.springboot.designpatterns.singleton;

import java.io.ObjectStreamException;

/**
 * @ClassName StaticInnerClassSingleton
 * @Description 静态内部类（也可用静态代码块） 线程安全 依赖ClassLoad
 * @Author chun.yin
 * @Date 2020/4/29 10:26
 * @Version 1.0
 **/

public class StaticInnerClassSingleton {

    /**
     * 内部类的执行依赖外部类的调用
     */
    private static class MySingletonHolder {
        private static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton() {
        // 避免通过反射调用构造方法调用
        if (MySingletonHolder.instance != null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    /**
     * 使用静态内部类可以保证线程安全但是如果对象被序列化然后反序列化后
     * 得到对象仍然会导致単例失效
     * @return
     */
    public static StaticInnerClassSingleton getInstance() {
        return MySingletonHolder.instance;
    }

    /**
     * 该方法在反序列化时会被调用，该方法不是接口定义的方法，是约定俗成的
     * 就是java为了解决序列化破坏单例的一种解决方案，在序列化的时候会判断对象是否有这个方法
     * 如果有会调用这个方法覆盖序列化创建的对象，之前反序列化出来的对象会被jvm回收
     * @return
     * @throws ObjectStreamException
     */
    protected Object readResolve() {
        System.out.println("调用了readResolve方法！");
        return MySingletonHolder.instance;
    }

}
