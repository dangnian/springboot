package com.dangnian.springboot.algorithm.huawei.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 求一个byte数字对应的二进制数字中1的最大连续数，例如3的二进制为00000011，最大连续2个1
 */
public class GetMaxBit {

    public static void main(String[] args) throws IOException {
        BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input=bReader.readLine())!=null) {
            int number=Integer.parseInt(input);
            String binaryString = Integer.toBinaryString(number);
            String[] string=binaryString.split("0");

            int num=0;
            for (String str:string) {
                num=Math.max(num, str.length());
            }
            System.out.println(num);

        }
    }
}
