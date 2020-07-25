package com.dangnian.springboot.algorithm.huawei.recursion;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 有一只兔子，从出生后第3个月起每个月都生一只兔子，小兔子长到第三个月后每个月又生一只兔子，假如兔子都不死，问每个月的兔子总数为多少？
 */
public class FBLXDemo4 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String month = null;
        while ((month = br.readLine()) != null) {
            System.out.println(f(Integer.valueOf(month)));
        }
    }

    private static int f(int month) {
        if (month == 1 || month == 2) {
            return 1;
        } else {
            return f(month -1) + f(month -2);
        }
    }


}
