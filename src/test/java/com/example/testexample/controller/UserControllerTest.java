package com.example.testexample.controller;

import com.example.testexample.model.User;
import com.example.testexample.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
//    if you use spring security
//    @WithMockUser
    public void allUsers() throws Exception {
        int id1 = 1;
        int id2 = 2;
        given(userService.findAll()).willReturn(Arrays.asList(new User().setId(id1), new User().setId(id2)));


        mockMvc
                .perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"));

        verify(userService).findAll();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void findById() throws Exception {
        int id = 1;
        given(userService.findById(id)).willReturn(new User().setId(id));


        mockMvc
                .perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(userService).findById(id);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void checker() throws Exception {
        mockMvc
                .perform(get("/checker"))
                .andExpect(status().isOk());


        verifyNoInteractions(userService);
        verifyNoMoreInteractions(userService);
    }

}