package com.example.kursovaja3.service;

import com.example.kursovaja3.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private Set<Question> actualQuestion;

    @BeforeEach
    public void setUp() {
        Question question1 = new Question("Что?", "Курсовая");
        Question question2 = new Question("Где?", "В академии");
        Question question3 = new Question("Когда?", "Завтра");
        Question question4 = new Question("Зачем?", "Так надо");
        actualQuestion = new HashSet<>(Set.of(question4,question3, question2, question1));
        when(questionService.getRandomQuestion()).thenReturn(question4,question3, question2, question1);
    }

    @Test
    public void shouldThrowExceptionWhenAmountQuestionsMoreSizeCollectionThrowsExceptions() {
        when(questionService.getRandomQuestion())
                .thenThrow(ResponseStatusException.class);

        assertThrows(ResponseStatusException.class, () -> questionService.getRandomQuestion());
    }

    @Test
    public void shouldReturnRandomQuestions() {

        final int amount = 4;

        Collection<Question> result = examinerService.getRandomQuestions(amount);

        assertArrayEquals(actualQuestion.toArray(), result.toArray());
    }
}
