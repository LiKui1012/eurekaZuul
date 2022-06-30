package com.provider.provider3rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Provider3rdApplication {

    public static void main(String[] args) {
        SpringApplication.run(Provider3rdApplication.class, args);
    }

}
