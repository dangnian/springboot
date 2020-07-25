package com.dangnian.springboot.algorithm.huawei.string;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseWord {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line=br.readLine())!=null){
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if ((c>='a' && c<='z')|| ( c>= 'A' && c<='Z')) {
                    sb.append(c);
                } else {
                    sb.append(" ");
                }
            }
            String[] str=sb.toString().trim().split(" ");
            StringBuffer s2=new StringBuffer();
            for (int j=str.length-1;j>=0;j--){
                if (!(str[j].equals(" "))){
                    s2.append(str[j]);
                }
                if (j>0){
                    s2.append(" ");
                }
            }
            System.out.println(s2.toString());
        }

    }
}

