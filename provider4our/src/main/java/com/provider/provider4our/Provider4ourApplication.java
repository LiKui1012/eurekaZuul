package com.provider.provider4our;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.db.database.*","com.provider.provider4our.*"})
@EnableJpaRepositories(basePackages = {"com.db.database.repository","com.provider.provider4our.*"})
@EntityScan(basePackages = {"com.db.database.model","com.provider.provider4our.*"})
//@ComponentScan(basePackages={"com.db.database.*", "com.provider.provider4our.*"})
//@MapperScan("com.db.database.repository")
@EnableEurekaClient
//provider4our引用了database，因为使用的jpa，需要EnableJpaRepositories而不是MapperScan
//MapperScan主要扫描的是mapper，会自动创建serviceImp，如果只是ComponentScan，只会扫描到这个bean，
//并不会自动创建实现类
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
