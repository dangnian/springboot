package com.dangnian.springboot.algorithm.huawei.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 合法IP
 */
public class LegalIP {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String readLine = null;
        while ((readLine = br.readLine()) != null) {
            String[] strs = readLine.split("\\.");
            for (int i = 0; i< strs.length; i++) {
                if (Integer.valueOf(strs[i]) > 0 && Integer.valueOf(strs[i]) <= 256){
                    if (i == strs.length - 1){
                        System.out.println("YSE");
                        break;
                    }
                    continue;
                }else {
                    System.out.println("N0");
                    break;
                }
            }
        }
    }
}
