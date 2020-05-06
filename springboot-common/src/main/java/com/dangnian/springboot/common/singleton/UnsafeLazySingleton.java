package com.dangnian.springboot.common.singleton;

/**
 * @ClassName UnsafeLazySingleton
 * @Description 懒汉式単例 线程不安全
 * @Author chun.yin
 * @Date 2020/4/29 9:59
 * @Version 1.0
 **/

public class UnsafeLazySingleton {

    private static UnsafeLazySingleton instance = null;

    private UnsafeLazySingleton() {};

    public static UnsafeLazySingleton getInstance() {
        // 线程不安全，多个线程执行到这里会导致単例失效
        if (instance != null) {
            try {
                // 模拟对象创建过程时间
                Thread.sleep(300);
                instance = new UnsafeLazySingleton();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
