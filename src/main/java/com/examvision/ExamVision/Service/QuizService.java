package com.examvision.ExamVision.Service;

import com.examvision.ExamVision.Entity.Quiz;
import com.examvision.ExamVision.Entity.Topic;
import com.examvision.ExamVision.Repository.QuizRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Transactional
    public Quiz addQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    public List<Quiz> getQuizzes() {
        return quizRepo.findAll();
    }

    public Quiz getQuiz(Long quizId) {
        return quizRepo.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
    }

    @Transactional
    public Quiz updateQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Transactional
    public void deleteQuiz(Long quizId) {
        quizRepo.deleteById(quizId);
    }

    public List<Quiz> getQuizByTopic(Topic topic) {
        return quizRepo.findByTopic(topic);
    }
}
