package com.dangnian.springboot.algorithm.threadLocal;

public class ThreadLocalDemo {

    public static ThreadLocal<Integer> num = ThreadLocal.withInitial(() -> 0);

    /**
     * 斐波拉契散列黄金分割数 0x61c88647
     * @param args
     */
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i=0;i<5;i++) {
            threads[i] = new Thread(()-> {
                int tempory = num.get();
                num.set(tempory + 10);
                System.out.println(Thread.currentThread().getName() + "-" + num.get());

            });
        }
        for (int i=0;i<5;i++) {
            threads[i].start();
        }
    }


}
