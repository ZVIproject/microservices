package com.zviproject.dbservice.configuration;

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

    private static final String QUEUE_NAME_GET_ALL = "get_all";

    private static final String QUEUE_NAME_GET_SINGLE = "get_single";


    private static final String GET_KEY = "get.#";

    private static final String TOPIC_NAME = "get-statistic";

    @Value(value = "${spring.rabbitmq.host}")
    private String rabbitHost;

    @Value(value = "${spring.rabbitmq.port}")
    private int rabbitPort;


    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(rabbitHost, rabbitPort);
    }

    @Bean
    public Queue queueSingle() {
        return new Queue(QUEUE_NAME_GET_SINGLE, false);
    }

    @Bean
    public Queue queueAll() {
        return new Queue(QUEUE_NAME_GET_ALL, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_NAME);
    }

    @Bean
    public Binding bindingSingle() {
        return BindingBuilder.bind(queueSingle()).to(exchange()).with(GET_KEY);
    }

    @Bean
    public Binding bindingAll() {
        return BindingBuilder.bind(queueAll()).to(exchange()).with(GET_KEY);
    }

//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }

    //@Bean
    //MessageListenerAdapter listenerAdapter(Receiver receiver) {
    //    return new MessageListenerAdapter(receiver, "receiveMessage");
    //}
}
