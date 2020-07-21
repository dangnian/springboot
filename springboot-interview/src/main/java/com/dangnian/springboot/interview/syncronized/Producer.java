package com.dangnian.springboot.interview.syncronized;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

    private Queue<String> queue;

    private int maxSize = 5;

    public Producer(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int i = 0;
        while(true) {
            synchronized (queue) {
                i++;
                while(queue.size() >= maxSize) {
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
                System.out.println("生产者生产消息：" + i);
                queue.add(String.valueOf(i));
                queue.notify();
            }

        }
    }
}
