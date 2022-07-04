package com.provider.provider4our;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//Ribbon+RestTemplate 调用微服务加入熔断 后续加入分表
@SpringBootApplication
@EnableEurekaClient
public class Provider4ourApplication {

    public static void main(String[] args) {
        SpringApplication.run(Provider4ourApplication.class, args);
    }


    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
