package com.examvision.ExamVision.Service;

import com.examvision.ExamVision.Entity.Topic;
import com.examvision.ExamVision.Repository.TopicRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepo topicRepo;

    @Transactional
    public Topic addTopic(Topic topic) {
        return topicRepo.save(topic);
    }

    public List<Topic> getTopics() {
        return topicRepo.findAll();
    }

    public Topic getTopic(Long topicId) {
        return topicRepo.findById(topicId).orElseThrow(() -> new RuntimeException("Topic not found"));
    }

    @Transactional
    public Topic updateTopic(Topic topic) {
        return topicRepo.save(topic);
    }

    @Transactional
    public void deleteTopic(Long topicId) {
        Topic topic = getTopic(topicId);
        topicRepo.delete(topic);
    }
}
