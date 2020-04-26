package com.dangnian.springboot.web.controller;

import com.dangnian.springboot.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Controller
 * @Description TODO
 * @Author chun.yin
 * @Date 2020/4/26 14:05
 * @Version 1.0
 **/

@RestController
@RequestMapping("/testController")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        return "hello World";
    }


    @GetMapping("testExecutor")
    public void testExecutor() {
        testService.executor();
        System.out.println("main thread is over");

    }
}
