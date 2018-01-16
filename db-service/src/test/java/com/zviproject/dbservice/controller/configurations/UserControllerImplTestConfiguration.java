package com.zviproject.dbservice.controller.configurations;

import com.zviproject.dbservice.component.repository.UserRepository;
import com.zviproject.dbservice.controller.UserControllerImpl;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;

@TestConfiguration
@PropertySource("classpath:application-test_configuration.properties")
public class UserControllerImplTestConfiguration {

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

    @Bean
    public UserControllerImpl userControllerImpl() {
        return new UserControllerImpl();
    }

    @Autowired
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(new CachingConnectionFactory(rabbitHost));
    }

    @PostConstruct
    public void showData() {
        System.out.println(rabbitHost);
    }
}
