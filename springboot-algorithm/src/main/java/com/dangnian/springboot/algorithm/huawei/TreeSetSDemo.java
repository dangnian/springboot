package com.dangnian.springboot.algorithm.huawei;

import java.util.Scanner;
import java.util.TreeSet;

public class TreeSetSDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            int count = sc.nextInt();
            TreeSet<Integer> treeSet = new TreeSet<Integer>();
            while(count-->0 && sc.hasNextInt()){
                treeSet.add(sc.nextInt());
            }
            treeSet.forEach(key -> System.out.println(key));
        }


    }

}