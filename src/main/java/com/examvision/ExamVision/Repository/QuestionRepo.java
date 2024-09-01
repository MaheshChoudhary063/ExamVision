package com.examvision.ExamVision.Repository;

import com.examvision.ExamVision.Entity.Question;
import com.examvision.ExamVision.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Long> {
    List<Question> findByQuiz(Quiz quiz);
}
