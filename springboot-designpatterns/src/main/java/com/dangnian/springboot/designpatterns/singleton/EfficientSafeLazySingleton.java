package com.dangnian.springboot.designpatterns.singleton;

/**
 * @ClassName EfficientSafeLazySingleton
 * @Description TODO
 * @Author chun.yin
 * @Date 2020/4/29 10:11
 * @Version 1.0
 **/

public class EfficientSafeLazySingleton {

    private static volatile EfficientSafeLazySingleton instance = null;

    private EfficientSafeLazySingleton(){}

    public static EfficientSafeLazySingleton getInstance() {
        if (instance == null) {
            synchronized (InefficientSafeLazySingleton.class) {
                if (instance == null) {
                    // 模拟对象创建过程时间
                    try {
                        Thread.sleep(300);
                        instance = new EfficientSafeLazySingleton();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }
}
