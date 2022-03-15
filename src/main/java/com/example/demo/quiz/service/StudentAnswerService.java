package com.example.demo.quiz.service;

import com.example.demo.quiz.model.*;
import com.example.demo.quiz.repository.StudentAnswerRepository;
import com.example.demo.quiz.request.StudentAnswerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
@AllArgsConstructor
public class StudentAnswerService {
    private final QuestionService questionService;
    private final StudentAnswerRepository studentAnswerRepository;

    public void addStudentAnswer(StudentAnswerRequest request, QuizSession quizSession, Question question) {
        Collection<QuestionAnswer> correctAnswers = questionService.getAnswersFromQuestion(question.getId());
        Collection<StudentAnswer> studentAnswers = getAnswersFromQuestionFromQuizSession(quizSession, question);
        System.out.println("\n\n\n\nadd\n" + studentAnswers.size() + "\n\n\n\n\n");
        if (studentAnswers.size() == 0) {
            Iterator<QuestionAnswer> it = correctAnswers.iterator();
            StudentAnswer studentAnswer0 = new StudentAnswer(it.next(), quizSession, request.isAnswerCorrect0());
            studentAnswerRepository.save(studentAnswer0);
            StudentAnswer studentAnswer1 = new StudentAnswer(it.next(), quizSession, request.isAnswerCorrect1());
            studentAnswerRepository.save(studentAnswer1);
            StudentAnswer studentAnswer2 = new StudentAnswer(it.next(), quizSession, request.isAnswerCorrect2());
            studentAnswerRepository.save(studentAnswer2);
            StudentAnswer studentAnswer3 = new StudentAnswer(it.next(), quizSession, request.isAnswerCorrect3());
            studentAnswerRepository.save(studentAnswer3);
        } else {
            Iterator<StudentAnswer> it = studentAnswers.iterator();
            StudentAnswer studentAnswer0 = it.next();
            studentAnswer0.setCorrect(request.isAnswerCorrect0());
            studentAnswerRepository.updateStudentAnswer(request.isAnswerCorrect0(), studentAnswer0.getId());
            StudentAnswer studentAnswer1 = it.next();
            studentAnswerRepository.updateStudentAnswer(request.isAnswerCorrect1(), studentAnswer1.getId());
            StudentAnswer studentAnswer2 = it.next();
            studentAnswerRepository.updateStudentAnswer(request.isAnswerCorrect2(), studentAnswer2.getId());
            StudentAnswer studentAnswer3 = it.next();
            studentAnswerRepository.updateStudentAnswer(request.isAnswerCorrect3(), studentAnswer3.getId());
        }
    }

    public Collection<StudentAnswer> getAnswersFromQuestionFromQuizSession(QuizSession quizSession, Question question) {
        Collection<StudentAnswer> studentAnswers = studentAnswerRepository.getAnswersFromQuizSession(quizSession);
        studentAnswers.removeIf(studentAnswer -> !studentAnswer.getAnswer().getQuestion().getId().equals(question.getId()));
        System.out.println("\n\n\n\nget\n" + studentAnswers.size() + "\n\n\n\n\n");
        return studentAnswers;
    }
}
