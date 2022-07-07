package com.db.datasharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
//
@SpringBootApplication(scanBasePackages = {"com.db.database.*","com.db.datasharding.*"})
@EnableJpaRepositories(basePackages = {"com.db.database.repository","com.db.datasharding.*"})
@EntityScan(basePackages = {"com.db.database.model","com.db.datasharding.*"})
public class DatashardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatashardingApplication.class, args);
    }

}
