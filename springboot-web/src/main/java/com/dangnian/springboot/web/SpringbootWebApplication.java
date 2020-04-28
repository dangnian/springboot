package com.dangnian.springboot.web;

import com.dangnian.springboot.web.config.AsyncConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author chun.yin
 */
@SpringBootApplication(scanBasePackages = "com.dangnian.springboot")
public class SpringbootWebApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.registerBean(AsyncConfig.class);
        ac.refresh();
        SpringApplication.run(SpringbootWebApplication.class, args);
    }

}
