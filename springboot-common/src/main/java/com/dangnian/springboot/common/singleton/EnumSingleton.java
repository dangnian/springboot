package com.dangnian.springboot.common.singleton;

/**
 * @ClassName EnumSingleton
 * @Description 枚举类単例模式 线程安全 也是利用类加载机制
 * @Author chun.yin
 * @Date 2020/4/29 10:41
 * @Version 1.0
 **/

public class EnumSingleton {

     private enum EnumSingletonFactory {

        singletonFactory;

         private MySingleton instance;

        /**
         * 枚举类的构造方法是在类加载的时候被实例化的
         */
        EnumSingletonFactory() {
            instance = new MySingleton();
        }

        public MySingleton getInstance() {
            return instance;
        }

         class MySingleton {
             public MySingleton() {
             }
         }

    }

    public static EnumSingletonFactory.MySingleton getInstance() {
        return EnumSingletonFactory.singletonFactory.getInstance();
    }

}
