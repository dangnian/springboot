package com.dangnian.springboot.algorithm.huawei.math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HundredRMBBuyHundredChicken {

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while(bf.readLine() != null){
            int a ,b;
            for(a=0;a<20;a++){
                for(b=0;a+b<100;b++){
                    int c = 100 -a -b;
                    if(c%3==0&&(100-5*a-3*b)==c/3){
                        System.out.println(a + " " + b + " " + c);
                    }
                }
            }
        }
    }
}
