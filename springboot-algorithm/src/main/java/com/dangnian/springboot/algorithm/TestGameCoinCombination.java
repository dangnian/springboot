package com.dangnian.springboot.algorithm;

import java.util.Random;

public class TestGameCoinCombination {

    public static void main(String[] args) {
        Random random = new Random();
       // find(random.nextInt(100), random.nextInt(100));
        find(50, 100);

    }

    public static void find(int n, int m) {
        System.out.println("有" + n + "枚游戏币,总值" + m);
        int totleTimes = 0;
        int successTimes = 0;
        // 1的可能
        for (int i=0; i<n; i++) {
            if (i > m) {
                break;
            }
            // 2的可能
            for (int j=0; j<n; j++) {
                if ((i + j*2) > m) {
                    break;
                }
                // 5的可能
                for (int k=0; k<n-i-j; k++ ) {
                    if ((i + j*2 + k*5) > m) {
                        break;
                    }
                    // 10的可能
                    for (int q=0; q<n-i-j-k; q++) {
                        totleTimes ++;
                        if ((i + j*2 + k*5 + q*10) == m) {
                            successTimes ++;
                            System.out.println("1分的游戏币：" + i + ",2分的游戏币：" + j + ",5分的游戏币：" + k + ",10分的游戏币：" + q);
                        }
                        if ((i + j*2 + k*5 + q*10) > m) {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("循环次数：" + totleTimes);
        System.out.println("可能的匹配数：" + successTimes);
    }


}
