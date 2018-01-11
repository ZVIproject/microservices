package com.zviproject.dbchangeservice.configuration;

import com.zviproject.dbchangeservice.enums.QueueKey;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class RabbitConfiguration {

    private static final String UPDATE_KEY = "update.#";

    private static final String TOPIC_NAME = "change-statistic";

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

    @Value("${spring.rabbitmq.port}")
    private int rabbitPort;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
            new CachingConnectionFactory(rabbitHost, rabbitPort);
        return connectionFactory;
    }

    @Bean
    public Queue queueCreate() {
        return new Queue(QueueKey.QUEUE_NAME_POST.getKey(), false);
    }

    @Bean
    public Queue queueUpdate() {
        return new Queue(QueueKey.QUEUE_NAME_PUT.getKey(), false);
    }

    @Bean
    public Queue queueDelete() {
        return new Queue(QueueKey.QUEUE_NAME_DELETE.getKey(), false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_NAME);
    }

    @Bean
    public Binding bindingCreate() {
        return BindingBuilder.bind(queueCreate()).to(exchange()).with(UPDATE_KEY);
    }

    @Bean
    public Binding bindingUpdate() {
        return BindingBuilder.bind(queueUpdate()).to(exchange()).with(UPDATE_KEY);
    }

    @Bean
    public Binding bindingDelete() {
        return BindingBuilder.bind(queueDelete()).to(exchange()).with(UPDATE_KEY);
    }
}
