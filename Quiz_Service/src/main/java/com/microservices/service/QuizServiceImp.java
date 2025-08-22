package com.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.dto.Question;
import com.microservices.dto.UserAnswerDTO;
import com.microservices.entity.Quiz;
import com.microservices.feign.QuizFeign;
import com.microservices.repository.QuizRepo;

@Service
public class QuizServiceImp {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizFeign quizFeign;

    public ResponseEntity<String> getQuiz(String category, int numQ, String title) {
        List<Long> questions = quizFeign.getRandomQuestions(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);

        return ResponseEntity.ok("Quiz Created Successfully");
    }

    public ResponseEntity<List<Question>> getQuizById(long quizId) {
        Quiz quiz = quizRepo.findById(quizId).orElseThrow();
        return quizFeign.getQuestionsByQuestionId(quiz.getQuestionIds());
    }

    public ResponseEntity<Integer> submitQuiz(List<UserAnswerDTO> answers) {
        return quizFeign.getScore(answers);
    }
}
