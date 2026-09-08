package com.innowise.qaproxy.service;

import com.innowise.qaproxy.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionAnswerService {

    // Список мапок, а каждой мапке ключь - это уникальный ID вопроса, а значение - сам объект с вопрос-ответом
    private List<Map<String, Question>> questionAnswers;
}
