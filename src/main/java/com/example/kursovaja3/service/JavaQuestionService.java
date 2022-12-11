package com.example.kursovaja3.service;

import com.example.kursovaja3.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Map<Integer, Question> randomQuestions = new HashMap<>();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    private void putRandomQuestionsMap() {
        Integer id = 0;
        for (Question question : questions) {
            randomQuestions.put(id, question);
            id++;
        }
    }

    @Override
    public Question getRandomQuestion() {
        putRandomQuestionsMap();
        Question randomQuestion = new Question("test", "test");
        Random random = new Random();
        Integer number = random.nextInt(questions.size());
        if (randomQuestions.containsKey(number)) {
            randomQuestion = randomQuestions.get(number);
        }
        return randomQuestion;
    }
}
