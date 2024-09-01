package com.examvision.ExamVision.Repository;

import com.examvision.ExamVision.Entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizResultRepo extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByUserId(Long userId);
}
