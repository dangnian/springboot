package com.dangnian.springboot.web.test.controller;

import com.dangnian.springboot.common.response.annotation.ResponseResult;
import com.dangnian.springboot.service.test.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chun.yin
 **/

@RestController
@RequestMapping("/testController")
@Api("测试类")
@ResponseResult
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/toPage")
    @ApiOperation("测试接口1")
    public String toPage(String args) {
        return args;
    }

    @GetMapping("/getObject")
    @ApiOperation("测试接口2")
    public Object getObject() {
        String s = new String("1");
        return s;
    }

    @GetMapping("/testExecutor")
    public void testExecutor() {
        testService.executor();
        System.out.println("main thread is over");

    }

}
