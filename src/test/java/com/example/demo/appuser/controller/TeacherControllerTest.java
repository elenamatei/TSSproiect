package com.example.demo.appuser.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest {
    @Autowired
    TeacherController teacherController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTeacherById() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders
                            .get("/teachers/1"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("").exists());
            String content = result.getResponse().getContentAsString();
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void enrollTeacherToClass() {
        assertThat(teacherController).isNotNull();



        System.out.println(teacherController.getTeacherById(1));


    }
}