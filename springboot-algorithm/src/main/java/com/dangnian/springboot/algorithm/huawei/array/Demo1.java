package com.dangnian.springboot.algorithm.huawei.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 从输入任意个整型数，统计其中的负数个数并求所有非负数的平均值，结果保留一位小数，如果没有非负数，则平均值为0
 * 本题有多组输入数据，输入到文件末尾，请使用while(cin>>)读入
 * 数据范围小于1e6
 */
public class Demo1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int positiveCount = 0;
        int negativeCount = 0;
        double positiverSum = 0.0;
        for (String str : strs) {
            double num = Double.parseDouble(str);
            if (num >=0) {
                positiveCount++;
                positiverSum += num;
            } else {
                negativeCount++;
            }
        }
        System.out.println(negativeCount);
        System.out.println(Math.round(positiverSum*10.0/positiveCount)/10.0);
    }

}
