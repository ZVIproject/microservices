package com.zviproject.statisticservice.configurations;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class RabbitConfiguration {

    @Value(value = "${spring.rabbitmq.host}")
    private String rabbitHost;

    @Value(value = "${spring.rabbitmq.port}")
    private int rabbitPort;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
            new CachingConnectionFactory(rabbitHost, rabbitPort);
        return connectionFactory;
    }


}