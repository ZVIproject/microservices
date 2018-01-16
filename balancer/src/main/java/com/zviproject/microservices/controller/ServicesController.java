package com.zviproject.microservices.controller;

import com.zviproject.microservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ServicesController {

    @Autowired
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/rest/v1/db/get/service/")
    public List<User> getAll() {
        return restTemplate.exchange("http://get-balancer/rest/v1/db/get/service/", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        }).getBody();
    }

    @GetMapping("/rest/v1/db/get/service/{userId}")
    public User getSingle(@PathVariable("userId") Long userId) {
        return restTemplate.getForObject("http://get-balancer/rest/v1/db/get/service/" + userId, User.class);
    }

    @PostMapping("/rest/v1/db/change/service/")
    public User createUser(@RequestBody User user) {
        return restTemplate.postForObject("http://change-balancer/rest/v1/db/change/service/", user, User.class);
    }


}
