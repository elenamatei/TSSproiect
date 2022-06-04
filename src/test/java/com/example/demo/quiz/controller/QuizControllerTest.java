package com.example.demo.quiz.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("get question answers on success")
    void getQuestionAnswersSuccess() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.get("/quizzes/questions/6/answers"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            System.out.println(content);
            Assertions.assertFalse(content.contains("[]"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("get question answers on fail")
    void getQuestionAnswersFail() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.get("/quizzes/questions/0/answers"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            System.out.println(content);
            Assertions.assertTrue(content.contains("[]"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("get questuion on success")
    void getQuestuionsSuccess() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.get("/quizzes/questions/6"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            Assertions.assertTrue(content.contains("Question"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("get questuion on fail")
    void getQuestuionsFail() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.get("/quizzes/questions/0"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            Assertions.assertTrue(content.contains("Question not found."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("get questuions from quiz on success")
    void getQuestuionsFromQuizSuccess() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.get("/quizzes/5/questions"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            Assertions.assertTrue(content.contains("[Question"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("get questuions from quiz on fail")
    void getQuestuionsFromQuizFail() {
        try {
            MvcResult result = (MvcResult) mockMvc.perform( MockMvcRequestBuilders.get("/quizzes/0/questions"))
                    .andExpect(status().isOk())
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            Assertions.assertTrue(content.contains("[]"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}