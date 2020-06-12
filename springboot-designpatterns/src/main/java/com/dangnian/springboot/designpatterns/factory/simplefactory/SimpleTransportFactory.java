package com.dangnian.springboot.designpatterns.factory.simplefactory;

/**
 * @ClassName 简单工厂的可扩展性不好
 * @Description TODO
 * @Author chun.yin
 * @Date 2020/6/12 17:31
 * @Version 1.0
 **/

public class SimpleTransportFactory {

    public Car getCar() {
        // before processing 权限 校验 日志 等在创建对象过程中需要做的事
        return new Car();
    }

    public Ship getShip() {
        // before processing
        return new Ship();
    }

    public Plane getPlan() {
        // before processing
        return new Plane();
    }
}
