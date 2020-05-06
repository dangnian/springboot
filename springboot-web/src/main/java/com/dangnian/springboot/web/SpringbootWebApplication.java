package com.dangnian.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chun.yin
 */
@SpringBootApplication(scanBasePackages = {"com.dangnian.springboot.web",
        "com.dangnian.springboot.common", "com.dangnian.springboot.service"})
public class SpringbootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
    }

}
