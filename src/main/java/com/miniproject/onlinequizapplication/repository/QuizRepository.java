package com.miniproject.onlinequizapplication.repository;

import com.miniproject.onlinequizapplication.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByIsActiveTrue();
}
