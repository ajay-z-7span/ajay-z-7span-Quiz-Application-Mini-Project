package com.miniproject.onlinequizapplication.repository;

import com.miniproject.onlinequizapplication.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
