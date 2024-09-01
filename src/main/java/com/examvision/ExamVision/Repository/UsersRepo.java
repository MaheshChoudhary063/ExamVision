package com.examvision.ExamVision.Repository;

import com.examvision.ExamVision.Entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);
}