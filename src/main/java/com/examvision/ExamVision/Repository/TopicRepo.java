package com.examvision.ExamVision.Repository;

import com.examvision.ExamVision.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic, Long> {
}
