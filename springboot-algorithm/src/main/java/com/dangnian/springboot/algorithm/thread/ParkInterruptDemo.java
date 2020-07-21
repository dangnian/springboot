package com.dangnian.springboot.algorithm.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ParkInterruptDemo {


    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
       /* Thread t = new Thread(() -> {
            System.out.println("t线程开始执行");
            lock.lock();
            System.out.println("t线程抢占锁");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("t线程释放锁");
        });*/
        Thread t2 = new Thread(() -> {
            System.out.println("t2线程开始执行");
            LockSupport.park();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2进入循环");
            }
            System.out.println("t2线程中断");
        });
        /*t.start();
        TimeUnit.SECONDS.sleep(2);*/
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("开始中断");
        t2.interrupt();

    }
}
