package com.example.kursovaja3.service;

import com.example.kursovaja3.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getRandomQuestions(int amount);
}