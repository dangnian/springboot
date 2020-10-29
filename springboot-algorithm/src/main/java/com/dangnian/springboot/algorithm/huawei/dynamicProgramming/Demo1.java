package com.dangnian.springboot.algorithm.huawei.dynamicProgramming;

import java.util.Scanner;

/**
 * 对于不同的字符串，我们希望能有办法判断相似程度，我们定义了一套操作方法来把两个不相同的字符串变得相同，具体的操作方法如下：
 *
 * 1 修改一个字符，如把“a”替换为“b”。
 *
 * 2 增加一个字符，如把“abdd”变为“aebdd”。
 *
 * 3 删除一个字符，如把“travelling”变为“traveling”。
 *
 * 比如，对于“abcdefg”和“abcdef”两个字符串来说，我们认为可以通过增加和减少一个“g”的方式来达到目的。上面的两种方案，都只需要一次操作。把这个操作所需要的次数定义为两个字符串的距离，而相似度等于“距离＋1”的倒数。也就是说，“abcdefg”和“abcdef”的距离为1，相似度为1/2=0.5.
 *
 * 给定任意两个字符串，你是否能写出一个算法来计算出它们的相似度呢？
 */
public class Demo1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            String a = scan.nextLine();
            String b = scan.nextLine();

            // 求出两个字符串中较长的那个字符串长度
            int lenA = a.length();
            int lenB = b.length();

            int[][] dp = new int[lenA + 1][lenB + 1];

            // 初始化
            for (int i = 0; i <= lenB; i++) {
                dp[0][i] = i;
            }

            for (int i = 0; i <= lenA; i++) {
                dp[i][0] = i;
            }

            for (int i = 1; i <= lenA; i++) {
                for (int j = 1; j <= lenB; j++) {
                    if (a.charAt(i-1) == b.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    } else{
                        dp[i][j] = Math.min(dp[i-1][j] , dp[i][j-1]);
                        dp[i][j] = Math.min(dp[i-1][j-1] , dp[i][j]) + 1;
                    }
                }
            }

            int res = dp[lenA][lenB] + 1;
            System.out.println("1/"+ res);

        }

        scan.close();
    }
}
