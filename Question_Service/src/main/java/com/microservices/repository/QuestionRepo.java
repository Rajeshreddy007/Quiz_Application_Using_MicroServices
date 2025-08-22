package com.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservices.entity.Questions;

@Repository
public interface QuestionRepo extends JpaRepository<Questions, Long> {

	List<Questions> findByCategory(String category);

	@Query(value = "SELECT * FROM questions q WHERE q.id IN :questionId", nativeQuery = true)
	List<Questions> findAllById(List<Integer> questionId);

	@Query(value = "SELECT q.id FROM questions q WHERE q.category = :category ORDER BY RAND()", nativeQuery = true)
	List<Long> findRandomQuestions(@Param("category") String category);

}