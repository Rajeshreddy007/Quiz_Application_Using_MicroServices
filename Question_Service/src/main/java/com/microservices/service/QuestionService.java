package com.microservices.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.microservices.entity.Questions;

public interface QuestionService {
	ResponseEntity<List<Questions>> getAllQuestions();

	ResponseEntity<Questions> getQuestionById(Long id);

	ResponseEntity<Questions> addQuestion(Questions question);

	ResponseEntity<Questions> updateQuestion(Long id, Questions question);

	ResponseEntity<String> deleteQuestion(Long id);

	ResponseEntity<List<Questions>> getQuestionsByCategory(String category);
}
