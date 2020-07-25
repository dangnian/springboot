package com.dangnian.springboot.algorithm.huawei.recursion;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 请编写一个函数（允许增加子函数），计算n x m的棋盘格子（n为横向的格子数
 * ，m为竖向的格子数）沿着各自边缘线从左上角走到右下角，总共有多少种走法，要求不能走回头路，即：只能往右和往下走，不能往左和往上走。
 */
public class FBLXDemo3 {

    public static void main(String[] args) throws IOException {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = bReader.readLine()) != null) {
            int n = Integer.valueOf(line.substring(0, line.indexOf(" ")));
            int m = Integer.valueOf(line.substring(line.indexOf(" ") + 1));
            System.out.println(f(n , m));
        }
    }

    public static int f(int n, int m) {
        if (n == 0 && m == 0) {
            return 1;
        } else if (n == 0 && m > 0) {
            return f(n, m - 1);
        } else if (n > 0 && m == 0) {
            return f(n - 1, m);
        } else {
            return f(n - 1, m) + f(n, m - 1);
        }
    }


}
