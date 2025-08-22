package com.microservices.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.dto.Question;
import com.microservices.dto.UserAnswerDTO;

@FeignClient("Question-Service")
public interface QuizFeign {

    @PostMapping("/question/generate/{category}/{numberOfQuestions}")
    ResponseEntity<List<Long>> getRandomQuestions(
            @PathVariable("category") String category,
            @PathVariable("numberOfQuestions") int numQ
    );

    @PostMapping("/question/getScore")
    ResponseEntity<Integer> getScore(@RequestBody List<UserAnswerDTO> userAnswers);

    @PostMapping("/question/getByQuestionId")
    ResponseEntity<List<Question>> getQuestionsByQuestionId(@RequestBody List<Long> questionIds);

}
