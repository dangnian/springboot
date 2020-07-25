package com.dangnian.springboot.algorithm.huawei.recursion;

import java.util.Scanner;

/**
 * 递归
 * N个苹果放在M个盘子中,有多少不同的放法
 */
public class FBLXDemo2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            System.out.println(F(m, n));
        }
    }

    public static int F(int x, int y) {
        if (x == 0 || y == 1) {
            return 1;
        } else if (x < y) {
            return F(x, x);
        } else {
            // 递归减少
            // 原理: 如果x > y 则实际上是x-y个苹果如何放在y个盘子中依次递归 加上下一次
            // x个苹果放在y-1个盘子中
            return F(x, y - 1) + F(x - y, y);
        }
    }
}
