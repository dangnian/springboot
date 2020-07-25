package com.dangnian.springboot.algorithm.huawei.math;

import java.io.*;
import java.util.*;

/**
 * 正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，设计一个算法，求输入A和B的最小公倍数。
 */
public class LowestCommonMultiple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            // 使m>n
            if (m < n) {
                int temp = m;
                m = n;
                n = temp;
            }
            // 如果能够被整除则直接返回最大的数
            if (m%n == 0) {
                System.out.println(m);
            } else {
                // mn的乘积除以最大公约数
                System.out.println((m*n)/gcd(m , n));
            }
        }
    }

    /**
     * 求最大公约数：辗转相除法
     * 1\. a/b，令r为所得余数（0≤r<b） 若r=0，算法结束，a即为答案
     * 2\. 互换：置 a←b，b←r，并返回第一步
     */
    private static int gcd(int m, int n) {
        if (n == 0) return m;
        return gcd(n, m % n);
    }


}
