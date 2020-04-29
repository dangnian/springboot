package com.dangnian.springboot.web.singleton;

/**
 * @ClassName InefficientSafeLazySingleton
 * @Description 懒汉式単例 线程安全但是低效
 * @Author chun.yin
 * @Date 2020/4/29 10:05
 * @Version 1.0
 **/

public class InefficientSafeLazySingleton {

    private static InefficientSafeLazySingleton instance = null;

    private InefficientSafeLazySingleton() {
    }

    ;

    /**
     * 使用同步方法可以保证线程安全但是在高并发的情况下
     * 会存在大量的锁竞争，效率低下
     *
     * @return
     */
    public synchronized static InefficientSafeLazySingleton getInstance() {
        if (instance != null) {
            try {
                // 模拟对象创建过程时间
                Thread.sleep(300);
                instance = new InefficientSafeLazySingleton();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * 使用同步代码块也可以保证线程安全但是在高并发的情况下
     * 但是仍然会存在大量的锁竞争，效率低下
     *
     * @return
     */
    public static InefficientSafeLazySingleton getInstance2() {
        synchronized (InefficientSafeLazySingleton.class) {
            if (instance != null) {
                // 模拟对象创建过程时间
                try {
                    Thread.sleep(300);
                    instance = new InefficientSafeLazySingleton();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }
}
