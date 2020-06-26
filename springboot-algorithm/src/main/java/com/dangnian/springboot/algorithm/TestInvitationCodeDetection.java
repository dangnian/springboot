package com.dangnian.springboot.algorithm;

import java.util.HashMap;
import java.util.Random;

public class TestInvitationCodeDetection {

    /**
     * 邀请码检测
     */
    // 随机字符
    public static char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static HashMap<Character, Integer> map = new HashMap<>();
    // 字母和字符对应关系
    static {
      map.put('0',0);map.put('1',1);map.put('2',2);map.put('3',3);map.put('4',4);map.put('5',5);map.put('6',6);map.put('7',7);map.put('8',8);map.put('9',9);
      map.put('a',1);map.put('b',2);map.put('c',3);map.put('d',4);map.put('e',5);map.put('f',6);map.put('g',7);map.put('h',8);map.put('i',9);
      map.put('j',1);map.put('k',2);map.put('l',3);map.put('m',4);map.put('n',5);map.put('o',6);map.put('p',7);map.put('q',8);map.put('r',1);
      map.put('s',1);map.put('t',2);map.put('u',3);map.put('v',4);map.put('w',5);map.put('x',6);map.put('y',7);map.put('z',8);
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        for (;;) {
            // 生成随机数
            int num = 36;
            Random random = new Random();
            StringBuilder code = new StringBuilder();
            for (int i =0; i<16; i++) {
                int a = random.nextInt(num);
                code.append(chars[a]);
            }
            // 反转字符串
            String str = code.reverse().toString();
            // 处理结果
            int result = 0;
            for (int i =0; i<str.length(); i++) {
                if(((i + 1)&1) == 1) {
                    // 基数处理
                    result += map.get(str.charAt(i));
                } else {
                    // 偶数处理
                    result += map.get(str.charAt(i))*2/10 >= 1 ? map.get(str.charAt(i))*2 - 9 : map.get(str.charAt(i))*2;
                }
            }
            System.out.println("str=" + str + ",校验结果：" + (result%10 == 0 ? "ok" : "error"));
        }
    }
}
