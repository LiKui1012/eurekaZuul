package com.zuul.zuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@Slf4j
public class ZuulApplication {

    public static void main(String[] args) {
        log.info("info你好啊");
        log.error("error你好啊");
        SpringApplication.run(ZuulApplication.class, args);
    }

}
