package com.zviproject.dbservice.mvc;

import com.zviproject.dbservice.component.model.User;
import com.zviproject.dbservice.component.repository.UserRepository;
import com.zviproject.dbservice.controller.UserControllerImpl;
import org.hibernate.mapping.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerImplTest1 {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsers() throws Exception {
        doReturn(Collections.singletonList(new User())).when(userRepository).findAll();

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/v1/db/get/service/")
                                                    .content("").accept("application/json")
                                                    .contentType("application/json"))
                                                    .andExpect(MockMvcResultMatchers.status().isOk());
                                                    //.andExpect(MockMvcResultMatchers.jsonPath("{id:2, name:...}"));
     }


}