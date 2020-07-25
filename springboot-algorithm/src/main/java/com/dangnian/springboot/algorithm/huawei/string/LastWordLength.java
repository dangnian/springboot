package com.dangnian.springboot.algorithm.huawei.string;

import java.util.Scanner;

/**
 * 计算字符串最后一个单词的长度，单词以空格隔开
 */
public class LastWordLength {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String str = input.nextLine();
            System.out.println(str.length()-1-str.lastIndexOf(" "));
        }

    }

}
