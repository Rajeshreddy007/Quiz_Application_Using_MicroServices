package com.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dto.QuestionWrapper;
import com.microservices.dto.UserAnswerDTO;
import com.microservices.entity.Questions;
import com.microservices.service.QuestionServiceImp;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionServiceImp questionService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Questions>> getAllQuestions() {
		return questionService.getAllQuestions();
	}

	@GetMapping("/getBy/{Category}")
	public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable("Category") String category) {
		return questionService.getQuestionsByCategory(category);
	}

	@PostMapping("/add")
	public ResponseEntity<Questions> addQuestion(@RequestBody Questions question) {
		return questionService.addQuestion(question);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Questions> updateQuestion(@PathVariable("id") Long id, @RequestBody Questions question) {
		return questionService.updateQuestion(id, question);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Questions> getQuestionById(@PathVariable("id") Long id) {
		return questionService.getQuestionById(id);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable("id") Long id) {
		return questionService.deleteQuestion(id);
	}

	@PostMapping("generate/{category}/{numberOfQuestions}")
	public ResponseEntity<List<Long>> getRandomQuestions(@PathVariable("category") String category,
			@PathVariable("numberOfQuestions") int numberOfQuestions) {
		return questionService.getRandomQuestions(category, numberOfQuestions);
	}

	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<UserAnswerDTO> answers) {
		return questionService.getScore(answers);
	}

	@PostMapping("/getByQuestionId")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuestionId(@RequestBody List<Integer> questionId) {
		return questionService.getQuestionsByQuestionId(questionId);
	}
}