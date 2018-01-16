package com.zviproject.dbchangeservice.standalone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.zviproject.dbchangeservice.component.repository")
@EntityScan(basePackages = "com.zviproject.dbchangeservice.component.model")
@ComponentScan(basePackages = "com.zviproject.dbchangeservice")
@EnableEurekaClient
public class DbChangeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbChangeServiceApplication.class, args);
    }
}
