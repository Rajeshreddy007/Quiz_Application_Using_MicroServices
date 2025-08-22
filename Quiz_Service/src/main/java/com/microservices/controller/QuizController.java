package com.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dto.Question;
import com.microservices.dto.UserAnswerDTO;
import com.microservices.service.QuizServiceImp;

@RestController
@RequestMapping("/quiz")
public class QuizController { 

    @Autowired
    private QuizServiceImp quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(
            @RequestParam String category,
            @RequestParam int numQ,
            @RequestParam String title) {
        return quizService.getQuiz(category, numQ, title);
    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<List<Question>> getQuizById(@PathVariable long quizId) {
        return quizService.getQuizById(quizId);
    }
    

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<UserAnswerDTO> answers) {
        return quizService.submitQuiz(answers);
    }
}
