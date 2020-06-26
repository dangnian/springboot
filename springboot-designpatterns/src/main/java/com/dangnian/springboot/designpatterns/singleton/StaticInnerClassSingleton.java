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

    private StaticInnerClassSingleton() {}

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
     * @return
     * @throws ObjectStreamException
     */
    protected Object readResolve() throws ObjectStreamException {
        System.out.println("调用了readResolve方法！");
        return MySingletonHolder.instance;
    }

}
