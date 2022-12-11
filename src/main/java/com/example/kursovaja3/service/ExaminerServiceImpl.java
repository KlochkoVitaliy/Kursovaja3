package com.example.kursovaja3.service;

import com.example.kursovaja3.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService service;
    public ExaminerServiceImpl(QuestionService service) {
        this.service = service;
    }

    @Override
    public Collection<Question> getRandomQuestions(int amount) {
        if (amount > service.getAll().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Set<Question> randomQuestions = new HashSet<>();
        while (randomQuestions.size() != amount) {
            randomQuestions.add(service.getRandomQuestion());
        }
        return randomQuestions;
    }
}
