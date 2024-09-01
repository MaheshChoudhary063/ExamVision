package com.examvision.ExamVision.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quiz_results")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizResId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "total_obtained_marks", nullable = false)
    private float totalObtainedMarks;

    @Column(name = "attempt_datetime", nullable = false)
    private String attemptDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
}
