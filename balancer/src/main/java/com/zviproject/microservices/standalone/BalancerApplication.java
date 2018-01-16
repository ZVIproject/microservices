package com.zviproject.microservices.standalone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.zviproject.microservices")
@RibbonClients({
    @RibbonClient(name = "get-balancer"),
    @RibbonClient(name = "change-balancer")
})
public class BalancerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BalancerApplication.class, args);
    }
}
