package com.examvision.ExamVision.Service;

import com.examvision.ExamVision.Entity.Question;
import com.examvision.ExamVision.Entity.Quiz;
import com.examvision.ExamVision.Repository.QuestionRepo;
import com.examvision.ExamVision.Repository.QuizRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private QuizRepo quizRepo;

    @Transactional
    public Question addQuestion(Question question) {
        Quiz quiz = quizRepo.findById(question.getQuiz().getQuizId()).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setNumOfQuestions(quiz.getNumOfQuestions() + 1);
        quizRepo.save(quiz);
        return questionRepo.save(question);
    }

    public List<Question> getQuestions() {
        return questionRepo.findAll();
    }

    public Question getQuestion(Long quesId) {
        return questionRepo.findById(quesId).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Transactional
    public Question updateQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Transactional
    public void deleteQuestion(Long questionId) {
        Question question = getQuestion(questionId);
        Quiz quiz = question.getQuiz();
        quiz.setNumOfQuestions(quiz.getNumOfQuestions() - 1);
        quizRepo.save(quiz);
        questionRepo.delete(question);
    }

    public List<Question> getQuestionsByQuiz(Quiz quiz) {
        return questionRepo.findByQuiz(quiz);
    }
}
