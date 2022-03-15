package com.example.demo.quiz.service;

import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.QuestionAnswer;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.repository.QuestionAnswerRepository;
import com.example.demo.quiz.repository.QuestionRepository;
import com.example.demo.quiz.repository.QuizRepository;
import com.example.demo.quiz.request.QuestionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuizRepository quizRepository;
    private final QuestionAnswerService answerService;

    public Question addQuestion(long quizId, QuestionRequest request) {
        Quiz quiz = quizRepository.getById(quizId);
        Question question = new Question(quiz, request.getQuestionText(), request.getScore());
        questionRepository.save(question);
        QuestionAnswer answer1 = answerService.addAnswer(question, request.getAnswer1(), request.isAnswer1Correct());
        questionAnswerRepository.save(answer1);
        QuestionAnswer answer2 = answerService.addAnswer(question, request.getAnswer2(), request.isAnswer2Correct());
        questionAnswerRepository.save(answer2);
        QuestionAnswer answer3 = answerService.addAnswer(question, request.getAnswer3(), request.isAnswer3Correct());
        questionAnswerRepository.save(answer3);
        QuestionAnswer answer4 = answerService.addAnswer(question, request.getAnswer4(), request.isAnswer4Correct());
        questionAnswerRepository.save(answer4);
        return question;
    }

    public Question getQuestionById(long id) {
        return questionRepository.getById(id);
    }

    public Collection<QuestionAnswer> getAnswersFromQuestion(Long id) {
        return questionAnswerRepository.getAnswersFromQuestion(questionRepository.getById(id));
    }
}
