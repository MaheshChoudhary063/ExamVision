package com.examvision.ExamVision.Controller;

import com.examvision.ExamVision.Entity.QuizResult;
import com.examvision.ExamVision.Entity.UserModel;
import com.examvision.ExamVision.Service.EmailService;
import com.examvision.ExamVision.Service.QuizResultService;
import com.examvision.ExamVision.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-results")
public class QuizResultController {

    @Autowired
    private QuizResultService quizResultService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<QuizResult> addQuizResult(@RequestBody QuizResult quizResult) {
        QuizResult savedQuizResult = quizResultService.addQuizResult(quizResult);
        sendResultEmail(savedQuizResult);
        return ResponseEntity.ok(savedQuizResult);
    }

    @GetMapping("/")
    public ResponseEntity<List<QuizResult>> getQuizResults() {
        return ResponseEntity.ok(quizResultService.getQuizResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResult> getQuizResult(@PathVariable("id") Long quizResId) {
        return ResponseEntity.ok(quizResultService.getQuizResult(quizResId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuizResult>> getQuizResultsByUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(quizResultService.getQuizResultsByUser(userId));
    }

    @PostMapping("/sendResultEmail")
    public ResponseEntity<String> sendResultEmail(@RequestBody QuizResult quizResult) {
        try {
            UserModel user = userService.getUsersById(Long.valueOf(quizResult.getUserId())).getOurUsers();
            String emailContent = "Your quiz result:\n" +
                    "Quiz ID: " + quizResult.getQuiz().getId() + "\n" +
                    "Total Marks: " + quizResult.getTotalObtainedMarks() + "\n" +
                    "Attempt Date: " + quizResult.getAttemptDatetime();
            emailService.sendEmail(user.getEmail(), "Quiz Result", emailContent);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }
    }

}
