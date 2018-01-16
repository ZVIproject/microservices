package com.zviproject.dbservice.controller;

import com.zviproject.dbservice.component.model.User;
import com.zviproject.dbservice.component.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/db/get/service")
@PropertySource("classpath:/application.properties")
public class UserControllerImpl {

    private static final Logger LOGGER = Logger.getLogger(UserControllerImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.host}")
    private  String rabbitHost;
    /**
     * Get user representation by username
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public User getUser(@PathVariable(name = "userId") Long userId) {
        sendRabbitMessageByQueueNameAndData("get_single", userId.toString());
        return userRepository.findOne(userId);
    }

    /**
     * Get users representations
     *
     * @return
     */
    @GetMapping("/")
    public List<User> getAllUsers() {
        LOGGER.error(rabbitHost);
        sendRabbitMessageByQueueNameAndData("get_all", "findAll");
        return userRepository.findAll();
    }

    private void sendRabbitMessageByQueueNameAndData(String queueName, String data) {
        try {
            rabbitTemplate.convertAndSend(queueName, data);
        } catch (AmqpException e) {
            logError("Rabbit ", e);
        }
    }

    private void logError(String message, Exception e) {
        LOGGER.error(message, e);
    }

}
