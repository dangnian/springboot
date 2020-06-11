package com.dangnian.springboot.designpatterns.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName CASSingleton
 * @Description 利用乐观锁实现线程安全的単例
 * @Author chun.yin
 * @Date 2020/4/29 10:47
 * @Version 1.0
 **/

public class CASSingleton {

    private static final AtomicReference<CASSingleton> INSTANCE = new AtomicReference<>();

    private CASSingleton() {}

    public static CASSingleton getInstance() {
        for (; ; ) {
           CASSingleton singleton = INSTANCE.get();
            if (singleton  != null) {
                return singleton;
            }
            try {
                // 模拟对象创建过程时间
                Thread.sleep(300);
                // 刚开始若有大量的线程执行到这里会产生大量的对象
                singleton = new CASSingleton();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 此处如果处于等待中一直执行不成功会导致一直在死循环中，对CPU造成较大的执行开销
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}
