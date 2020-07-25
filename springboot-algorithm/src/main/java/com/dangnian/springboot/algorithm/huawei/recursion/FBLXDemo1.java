package com.dangnian.springboot.algorithm.huawei.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 编写一个函数，传入一个int型数组，返回该数组能否分成两组，使得两组中各元素加起来的和相等，并且，
 * 所有5的倍数必须在其中一个组中，所有3的倍数在另一个组中（不包括5的倍数），能满足以上条件，返回true；不满足时返回false
 */
public class FBLXDemo1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = null;
        while ((str1 = br.readLine()) != null) {
            int num = Integer.valueOf(str1);
            String[] strs = br.readLine().split(" ");
            int[] remain = new int[num];
            int group5Count = 0;
            int group3Count = 0;
            int index = 0;
            for (String s: strs) {
                if (Integer.valueOf(s)%5 ==0) {
                    group5Count+=Integer.valueOf(s);
                } else if (Integer.valueOf(s)%3 ==0){
                    group3Count+=Integer.valueOf(s);
                } else {
                    remain[index++]=Integer.valueOf(s);
                }
            }
            int sub = Math.abs(group3Count-group5Count);
            System.out.println(f(index,0, 0, remain, sub));
        }
    }

    public static boolean f(int index, int num, int result, int[] array, int sub){
        if (index == num) {
            return Math.abs(result) == sub;
        } else {
            return (f(index, num+1, result+array[num], array, sub) || f(index, num+1, result-array[num], array, sub));
        }
    }
}
