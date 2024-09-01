package com.examvision.ExamVision.Controller;

import com.examvision.ExamVision.Entity.Quiz;
import com.examvision.ExamVision.Entity.Topic;
import com.examvision.ExamVision.Service.QuizService;
import com.examvision.ExamVision.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private TopicService topicService;  // Inject TopicService to retrieve Topic object

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.addQuiz(quiz));
    }

    @GetMapping("/")
    public ResponseEntity<List<Quiz>> getQuizzes() {
        return ResponseEntity.ok(quizService.getQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("id") Long quizId) {
        return ResponseEntity.ok(quizService.getQuiz(quizId));
    }

    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.updateQuiz(quiz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable("id") Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<Quiz>> getQuizByTopic(@PathVariable("topicId") Long topicId) {
        Topic topic = topicService.getTopic(topicId);
        return ResponseEntity.ok(quizService.getQuizByTopic(topic));
    }
    @PostMapping("/{quizId}/set-link")
    public ResponseEntity<String> setQuizUrl(@PathVariable Long quizId) {
        try {
            Quiz quiz = quizService.getQuiz(quizId);

            String url = "http://localhost:8080/api/quizzes/" + quizId;

            quiz.setLink(url);
            quizService.updateQuiz(quiz);
            if (quiz.getQuestions().isEmpty()) {
                quizService.deleteQuiz(quizId);
                return ResponseEntity.ok("Successfully set the quiz link -> " + url + " and deleted quiz because it had no questions");
            }

            return ResponseEntity.ok("Successfully set the quiz link -> " + url);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating quiz URL", e);
        }
    }
}
