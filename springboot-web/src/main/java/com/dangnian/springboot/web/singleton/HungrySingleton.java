package com.dangnian.springboot.web.singleton;

/**
 * @ClassName HungrySingleton
 * @Description 饿汉式単例
 * @Author chun.yin
 * @Date 2020/4/29 9:53
 * @Version 1.0
 **/

public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {}

    public static HungrySingleton getInstance() {
        return instance;
    }
}
