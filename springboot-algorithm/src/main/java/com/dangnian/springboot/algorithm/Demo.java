package com.dangnian.springboot.algorithm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String readLine = null;
        while ((readLine = br.readLine()) != null) {
            System.out.println(f(readLine.substring(2)));
        }


    }

    public static int f(String s) {
        int num = 0;
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                result += (s.charAt(i) - '0') * (int)Math.pow(16, num);
            } else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                result += (s.charAt(i) - 'A' + 10) * (int)Math.pow(16, num);
            } else if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                result += (s.charAt(i) - 'a' + 10) * (int)Math.pow(16, num);
            }
            num++;
        }
        return result;
    }

}