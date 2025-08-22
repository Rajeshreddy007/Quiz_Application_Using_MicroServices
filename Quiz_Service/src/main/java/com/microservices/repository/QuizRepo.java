package com.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.entity.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Long> {

}
