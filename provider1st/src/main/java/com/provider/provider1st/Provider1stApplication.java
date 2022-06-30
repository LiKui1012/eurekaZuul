package com.provider.provider1st;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Provider1stApplication {

    public static void main(String[] args) {
        SpringApplication.run(Provider1stApplication.class, args);
    }

}
