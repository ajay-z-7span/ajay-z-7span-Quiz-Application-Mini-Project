package com.miniproject.onlinequizapplication.repository;

import com.miniproject.onlinequizapplication.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result,Integer> {

    Result findByQuizIdAndUserId(Integer quizId, Integer userId);

    List<Result> findByUserId(Integer userId);

}
