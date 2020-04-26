package com.dangnian.springboot.service.test.impl;

import com.dangnian.springboot.service.test.TestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName testServiceImpl
 * @Description TODO
 * @Author chun.yin
 * @Date 2020/4/26 16:36
 * @Version 1.0
 **/

@Service
public class TestServiceImpl implements TestService {

    @Override
    @Async
    public void executor() {
        for (int i = 0; i < 5000; i++) {
            System.out.println("i am working");
        }

    }
}
