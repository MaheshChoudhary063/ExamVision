package com.examvision.ExamVision.Repository;

import com.examvision.ExamVision.Entity.Quiz;
import com.examvision.ExamVision.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizRepo extends JpaRepository<Quiz, Long> {
    List<Quiz> findByTopic(Topic topic);
}
