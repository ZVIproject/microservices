package com.zviproject.dbchangeservice.controller;

import com.zviproject.dbchangeservice.component.model.User;
import com.zviproject.dbchangeservice.component.repository.UserRepository;
import com.zviproject.dbchangeservice.enums.QueueKey;
import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.ConnectException;

@RestController
@RequestMapping("/rest/v1/db/change/service")
@PropertySource("classpath:application.properties")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

    /**
     * Save user into the database
     * @param user
     * @return
     */
    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        createMessageAndLoggsWithPort(QueueKey.QUEUE_NAME_POST);
        return userRepository.save(user);
    }

    /**
     * Delete user from the database by user id
     * @param userId
     * @return
     */
    @DeleteMapping("/{user_delete_id}")
    public void deleteUser(@PathVariable("user_delete_id") Long userId) {
        createMessageAndLoggsWithPort(QueueKey.QUEUE_NAME_DELETE);
        userRepository.delete(userId);
    }

    /**
     * Update user in the database
     * @param user
     * @return
     */
    @PutMapping("/{user_id}")
    public User updateUser(@RequestBody User user){
        createMessageAndLoggsWithPort(QueueKey.QUEUE_NAME_PUT);
        return userRepository.save(user);
    }

    private void createMessageAndLoggsWithPort(QueueKey queueKey) {
        try {
            rabbitTemplate.convertAndSend(queueKey.getKey(), "got");
        } catch (AmqpException e){
            logError("RabbitHost:\t" + rabbitHost , e);
        }
    }

    private void logError(String message, Exception e) {
        LOGGER.error(message, e);
    }
}
