package com.example.demo.quiz.service;

import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
import com.example.demo.appuser.service.ClassService;
import com.example.demo.appuser.service.StudentService;
import com.example.demo.appuser.service.TeacherService;
import com.example.demo.quiz.model.*;
import com.example.demo.quiz.repository.QuestionRepository;
import com.example.demo.quiz.repository.QuizRepository;
import com.example.demo.quiz.repository.QuizSessionRepository;
import com.example.demo.quiz.request.QuizRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizSessionRepository quizSessionRepository;
    private final QuestionRepository questionRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final QuestionService questionService;
    private final StudentAnswerService studentAnswerService;
    private final ClassService classService;

    public Quiz addQuiz(QuizRequest request) {
        Teacher author = teacherService.getTeacherById(request.getAuthorId());
        Quiz quiz = new Quiz(author, request.getName());
        Collection<Student> students = classService.getAllStudentsByClassId(request.getClassId());
        quiz.setStudentsEnrolled(students);
        quizRepository.save(quiz);
        return quiz;
    }

    public Quiz getQuizById (Long id) {
        return quizRepository.getById(id);
    }

    public Quiz publishQuiz(Long id) {
        Quiz quiz = quizRepository.getById(id);
        quiz.setPublished(true);
        quizRepository.save(quiz);
        return quiz;
    }

    public QuizSession attemptQuiz(Quiz quiz, Student student) {
        QuizSession quizSession = new QuizSession(quiz, student);
        quizSessionRepository.save(quizSession);
        return quizSession;
    }

    public QuizSession getQuizSessionById(Long id) {
        return quizSessionRepository.getById(id);
    }

    public Collection<QuizSession> getQuizSessionsByQuizId(Long id) {
        return quizSessionRepository.getSessionsFromQuiz(quizRepository.getById(id));
    }

    public int calculateScore(QuizSession quizSession) {
        Collection<Question> questions = getQuestionsFromQuiz(quizSession.getQuiz().getId());
        int score = 0;
        for(Iterator<Question> questionIterator = questions.iterator(); questionIterator.hasNext();) {
            boolean answerIsCorrect = true;
            Question question = questionIterator.next();
            Collection<QuestionAnswer> correctAnswers = questionService.getAnswersFromQuestion(question.getId());
            Collection<StudentAnswer> studentAnswers = studentAnswerService.
                                                        getAnswersFromQuestionFromQuizSession(quizSession, question);
            Iterator<QuestionAnswer> correctAnswersIterator = correctAnswers.iterator();
            Iterator<StudentAnswer> studentAnswersIterator = studentAnswers.iterator();
            while (correctAnswersIterator.hasNext() && studentAnswersIterator.hasNext()) {
                if (correctAnswersIterator.next().isCorrect() != studentAnswersIterator.next().isCorrect()) {
                    answerIsCorrect = false;
                }
            }
            if (answerIsCorrect) {
                score += question.getScore();
            }
        }
        return score;
    }

    public Collection<Question> getQuestionsFromQuiz(Long id) {
        return questionRepository.getQuestionFromQuiz(quizRepository.getById(id));
    }

    public Collection<Quiz> getQuizzesByAuthorId(Long teacherId) {
        return quizRepository.getQuizzesByAuthor(teacherService.getTeacherById(teacherId));
    }

    public Collection<Quiz> getQuizzesByStudentId(Long studentId) {
        return quizRepository.getQuizzesByStudent(studentService.getStudentById(studentId));
    }
}
