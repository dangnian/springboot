package com.dangnian.springboot.interview.syncronized;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestDemo {

    public static void main(String[] args) throws InterruptedException {
        Queue<String> queue = new LinkedList<>();
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
