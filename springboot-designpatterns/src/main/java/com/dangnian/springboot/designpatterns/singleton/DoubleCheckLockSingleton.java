package com.dangnian.springboot.designpatterns.singleton;

/**
 * @ClassName EfficientSafeLazySingleton
 * @Description TODO
 * @Author chun.yin
 * @Date 2020/4/29 10:11
 * @Version 1.0
 **/

public class DoubleCheckLockSingleton {

    private static volatile DoubleCheckLockSingleton instance = null;

    private DoubleCheckLockSingleton(){}

    public static DoubleCheckLockSingleton getInstance() {
        if (instance == null) {
            synchronized (InefficientSafeLazySingleton.class) {
                if (instance == null) {
                    // 模拟对象创建过程时间
                    try {
                        Thread.sleep(300);
                        instance = new DoubleCheckLockSingleton();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }
}
