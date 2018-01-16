package com.zviproject.dbservice.controller;

import com.zviproject.dbservice.component.model.User;
import com.zviproject.dbservice.component.repository.UserRepository;
import com.zviproject.dbservice.controller.configurations.UserControllerImplTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserControllerImplTestConfiguration.class)
@ContextConfiguration(classes = UserControllerImplTestConfiguration.class)
@PropertySource(value = "classpath:test_configuration.properties")
//@MockBean(classes = AnoutherClassForMockWithoutUsing.class)
public class UserControllerImplTest {


    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserControllerImpl userControllerImpl;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void getAllUsers() {

        doReturn(Collections.singletonList(createNewUser())).when(userRepository).findAll();

        assertEquals("Should be equals", userControllerImpl.getAllUsers().size(), 1);
    }

    private User createNewUser() {
        User user = new User();
        user.setPassword("password");
        user.setUsername("Username");
        user.setAge((byte) 12);
        user.setId(231L);
        return user;
    }

    @Test
    public void getUser() {

    }

}