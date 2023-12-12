package com.miniproject.onlinequizapplication.repository;

import com.miniproject.onlinequizapplication.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OptionRepository extends JpaRepository<Options,Integer> {
}
