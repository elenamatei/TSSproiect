package com.example.demo.appuser.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("get teacher by id on success")
    void getTeacherByIdSuccessful() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.get("/teachers/1"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("get teacher by id on fail")
    void getTeacherByIdFailed() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.get("/teachers/0"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("trying to enroll teacher to class on success")
    void tryEnrollToClassSuccessful() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.post("/teachers/1/enroll_to_class/1"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            Assertions.assertFalse(content.contains("not found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("trying to enroll teacher to class on fail")
    void tryEnrollToClassFailed() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.post("/teachers/0/enroll_to_class/1"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            Assertions.assertTrue(content.contains("not found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}