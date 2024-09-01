package com.examvision.ExamVision.Service;

import com.examvision.ExamVision.Entity.QuizResult;
import com.examvision.ExamVision.Repository.QuizResultRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizResultService {

    @Autowired
    private QuizResultRepo quizResultRepo;

    @Transactional
    public QuizResult addQuizResult(QuizResult quizResult) {
        return quizResultRepo.save(quizResult);
    }

    public List<QuizResult> getQuizResults() {
        return quizResultRepo.findAll();
    }

    public QuizResult getQuizResult(Long quizResId) {
        return quizResultRepo.findById(quizResId).orElseThrow(() -> new RuntimeException("Quiz result not found"));
    }

    public List<QuizResult> getQuizResultsByUser(Long userId) {
        return quizResultRepo.findByUserId(userId);
    }

}
