package com.examvision.ExamVision.Controller;

import com.examvision.ExamVision.Entity.Question;
import com.examvision.ExamVision.Entity.Quiz;
import com.examvision.ExamVision.Service.QuestionService;
import com.examvision.ExamVision.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;  // Inject QuizService to retrieve Quiz object

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestion(question));
    }

    @GetMapping("/")
    public ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.ok(questionService.getQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable("id") Long quesId) {
        return ResponseEntity.ok(questionService.getQuestion(quesId));
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long quesId) {
        questionService.deleteQuestion(quesId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable("quizId") Long quizId) {
        Quiz quiz = quizService.getQuiz(quizId);  // Fetch the Quiz object using QuizService
        return ResponseEntity.ok(questionService.getQuestionsByQuiz(quiz));
    }
}
