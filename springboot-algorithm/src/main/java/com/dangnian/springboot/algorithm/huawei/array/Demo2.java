package com.dangnian.springboot.algorithm.huawei.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 蛇形数列
 */
public class Demo2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String readLine = null;
        while ((readLine = br.readLine()) != null ) {
            int num = Integer.parseInt(readLine);
            for (int i = 1; i<= num; i++) {
                int start = 1+i*(i-1)/2;
                int step = i+1;
                for (int j=1; j<= num-i+1; j++){
                    if(j == num -i + 1) {
                        System.out.println(start);
                    } else {
                        System.out.print(start);
                    }
                    start+=step;
                    step++;
                }
            }
        }
    }
}
