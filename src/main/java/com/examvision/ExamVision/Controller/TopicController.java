package com.examvision.ExamVision.Controller;

import com.examvision.ExamVision.Entity.Topic;
import com.examvision.ExamVision.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping("/")
    public ResponseEntity<Topic> addTopic(@RequestBody Topic topic) {
        return ResponseEntity.ok(topicService.addTopic(topic));
    }

    @GetMapping("/")
    public ResponseEntity<List<Topic>> getTopics() {
        return ResponseEntity.ok(topicService.getTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable("id") Long topicId) {
        return ResponseEntity.ok(topicService.getTopic(topicId));
    }

    @PutMapping("/")
    public ResponseEntity<Topic> updateTopic(@RequestBody Topic topic) {
        return ResponseEntity.ok(topicService.updateTopic(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("id") Long topicId) {
        topicService.deleteTopic(topicId);
        return ResponseEntity.noContent().build();
    }
}
