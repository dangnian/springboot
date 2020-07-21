package com.dangnian.springboot.interview.syncronized;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{

    private Queue<String> queue;

    public Consumer(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者消费消息：" + queue.remove());
                queue.notify();
            }
        }

    }
}
