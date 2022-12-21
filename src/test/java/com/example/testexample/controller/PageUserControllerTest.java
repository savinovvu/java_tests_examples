package com.example.testexample.controller;

import com.example.testexample.model.Authority;
import com.example.testexample.model.User;
import com.example.testexample.security.SecurityService;
import com.example.testexample.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PageUserController.class)
class PageUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private SecurityService _0;

    @MockBean
    private BCryptPasswordEncoder _1;


    @Test
    public void contextLoads() {
        Assertions.assertThat(mockMvc).isNotNull();
    }
    @Test
    @WithMockUser(authorities = Authority.EDIT_USER)
    public void testModel() throws Exception {
        int id1 = 1;
        int id2 = 2;
        List<User> users = Arrays.asList(new User().setId(id1), new User().setId(id2));
        given(userService.findAll()).willReturn(users);


        mockMvc
                .perform(get("/web/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("/pages/user/index"))
                .andExpect(model().attribute("list", users));

        verify(userService).findAll();
        verifyNoMoreInteractions(userService);
    }


}