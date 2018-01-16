package com.zviproject.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.zviproject.dbservice")
@ComponentScan(basePackages = "com.zviproject.dbservice")
@EntityScan(basePackages = "com.zviproject.dbservice")
@EnableEurekaClient
public class DbGetServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DbGetServiceApplication.class, args);
    }
}


