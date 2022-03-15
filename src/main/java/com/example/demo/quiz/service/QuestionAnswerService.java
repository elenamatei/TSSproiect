package com.example.demo.quiz.service;

import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.QuestionAnswer;
import com.example.demo.quiz.repository.QuestionAnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionAnswerService {
    QuestionAnswerRepository answerRepository;

    public QuestionAnswer addAnswer(Question question, String answerText, boolean isTrue){
        QuestionAnswer answer = new QuestionAnswer(question, answerText, isTrue);
        answerRepository.save(answer);
        return answer;
    }
}
