package com.example.demo.quiz.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuizControllerTest {
    @Autowired
    QuizController quizController;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("get questuion answers on success")
    void getQuestuionAnswers() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.get("/quizzes/questions/1/answers"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            System.out.println(content);
//            Assertions.assertTrue(content.contains("firstName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}