package com.dangnian.springboot.algorithm;

import java.util.HashMap;
import java.util.Map;

public class TestDepartmentOptimization {

    /**
     * 部门优化
     */
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("GroupA", 10);
        map.put("GroupB", 7);
        map.put("GroupC", 5);
        map.put("GroupD", 4);
        // 最大组
        String maxGroupKey = "GroupA";
        int times = 0;
        while (times < 120) {
            // 最大组减3
            map.put(maxGroupKey, map.get(maxGroupKey) - 3);
            // 下一个最大组
            String nextMaxGroupKey = maxGroupKey;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                // 若不是最大的组则减一
                if (!maxGroupKey.equals(entry.getKey())) {
                    map.put(entry.getKey(), entry.getValue() + 1);
                    // 判断下一个最大组
                    if (entry.getValue() + 1 > map.get(nextMaxGroupKey)) {
                        nextMaxGroupKey = entry.getKey();
                    }
                }
            }
            maxGroupKey = nextMaxGroupKey;
            times ++;
        }
        System.out.println(map);
    }
}
