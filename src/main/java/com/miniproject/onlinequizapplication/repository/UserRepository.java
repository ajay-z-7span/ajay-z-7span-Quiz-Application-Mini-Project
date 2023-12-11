package com.miniproject.onlinequizapplication.repository;

import com.miniproject.onlinequizapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUserNameAndPassword(String userName,String password);

}
