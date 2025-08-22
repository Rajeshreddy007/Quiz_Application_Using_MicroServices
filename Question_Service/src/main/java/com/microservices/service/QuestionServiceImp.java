package com.microservices.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.dto.QuestionWrapper;
import com.microservices.dto.UserAnswerDTO;
import com.microservices.entity.Questions;
import com.microservices.repository.QuestionRepo;

@Service
public class QuestionServiceImp implements QuestionService {

	@Autowired
	private QuestionRepo questionRepo;

	@Override
	public ResponseEntity<List<Questions>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Questions> getQuestionById(Long id) {
		try {
			return new ResponseEntity<>(questionRepo.findById(id).orElse(null), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Questions> addQuestion(Questions question) {
		try {
			return new ResponseEntity<>((Questions) questionRepo.save(question), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Questions> updateQuestion(Long id, Questions question) {
		try {
			Questions existing = questionRepo.findById(id).orElse(null);
			if (existing != null) {
				question.setId(id);
				return new ResponseEntity<>(questionRepo.save(question), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> deleteQuestion(Long id) {
		try {
			questionRepo.deleteById(id);
			return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Deletion Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Long>> getRandomQuestions(String category, int numQ) {
		try {
			List<Long> all = questionRepo.findRandomQuestions(category);
			List<Long> randomSubset = all.stream().limit(numQ).toList();
			return new ResponseEntity<>(randomSubset, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<Integer> getScore(List<UserAnswerDTO> userAnswers) {
		try {
			if (userAnswers == null || userAnswers.isEmpty()) {
				return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
			}
			int score = 0;
			for (UserAnswerDTO userAnswer : userAnswers) {
				Questions question = questionRepo.findById(userAnswer.getQuestionId()).orElse(null);
				if (question != null && question.getAnswer() != null && userAnswer.getUserAnswer() != null
						&& question.getAnswer().equalsIgnoreCase(userAnswer.getUserAnswer())) {
					score++;
				}
			}
			return new ResponseEntity<>(score, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuestionId(List<Integer> questionId) {
		try {
			List<Questions> questions = questionRepo.findAllById(questionId);
			if (questions.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			List<QuestionWrapper> questionWrappers = questions.stream().map(q -> new QuestionWrapper(q.getId(),
					q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()))
					.collect(Collectors.toList());
			return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}