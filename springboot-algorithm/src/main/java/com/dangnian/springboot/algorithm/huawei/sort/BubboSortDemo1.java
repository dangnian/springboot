package com.dangnian.springboot.algorithm.huawei.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BubboSortDemo1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while ((line = br.readLine()) != null) {
            int count = Integer.parseInt(line);
            String[] strs = br.readLine().split(" ");
            int flag = Integer.parseInt(br.readLine());
            for (int i = 0; i < strs.length; i++) {
                for (int j = 0; j < strs.length - i - 1; j++) {
                    if (flag == 0) {
                        if ((Integer.parseInt(strs[j])) > (Integer.parseInt(strs[j+1]))){
                            String temp = strs[j+1];
                            strs[j+1]=strs[j];
                            strs[j]=temp;
                        }
                    } else if (flag == 1){
                        if ((Integer.parseInt(strs[j])) < (Integer.parseInt(strs[j+1]))){
                            String temp = strs[j+1];
                            strs[j+1]=strs[j];
                            strs[j]=temp;;
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<strs.length;i++){
                sb.append(strs[i]+" ");
            }
            System.out.println(sb.toString());
        }
    }
}
