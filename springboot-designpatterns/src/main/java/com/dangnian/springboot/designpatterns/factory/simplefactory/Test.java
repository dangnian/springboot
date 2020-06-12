package com.dangnian.springboot.designpatterns.factory.simplefactory;

/**
 * @ClassName Test
 * @Description TODO
 * @Author chun.yin
 * @Date 2020/6/12 17:40
 * @Version 1.0
 **/

public class Test {

    public static void main(String[] args) {
        SimpleTransportFactory transportFactory = new SimpleTransportFactory();
        Car car = transportFactory.getCar();
        car.move();
        Ship ship = transportFactory.getShip();
        ship.move();
        Plane plane =  transportFactory.getPlan();
        plane.move();
    }
}
