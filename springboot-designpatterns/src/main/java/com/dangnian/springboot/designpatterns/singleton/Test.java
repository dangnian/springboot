package com.dangnian.springboot.designpatterns.singleton;

import java.util.stream.IntStream;

/**
 * @ClassName Test
 * @Description TODO
 * @Author chun.yin
 * @Date 2020/6/11 14:14
 * @Version 1.0
 **/

public class Test {

    public static void main(String[] args) {
        IntStream.range(0,99).parallel().forEach(i -> new Thread(() -> System.out.println(UnsafeLazySingleton.getInstance().hashCode())).start());
    }
}
