package com.dangnian.springboot.web.test.controller;

import com.dangnian.springboot.common.response.annotation.IgnoreResponseResult;
import com.dangnian.springboot.common.response.enums.EResultCode;
import com.dangnian.springboot.common.response.exception.CommonRuntimeException;
import com.dangnian.springboot.entity.test.po.TestPO;
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
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/toPage")
    @ApiOperation("测试接口1")
    @IgnoreResponseResult
    public String toPage() {
        return "test/testPage";
    }

    @GetMapping("/getObject")
    @ApiOperation("测试接口2")
    public TestPO getObject() {
        TestPO po = new TestPO();
        po.setTestCode("007");
        po.setTestName("dangnian");
        return po;
    }

    @GetMapping("/testException")
    @ApiOperation("测试接口3")
    public int testException() {
        return 1/0;
    }

    @GetMapping("/testBusinessException")
    public void testBusinessException(){
        throw new CommonRuntimeException(EResultCode.PARAM_NOT_ILLEGAL, "无效参数");
    }

    @GetMapping("/testExecutor")
    public void testExecutor() {
        testService.executor();
        System.out.println("main thread is over");

    }

}
