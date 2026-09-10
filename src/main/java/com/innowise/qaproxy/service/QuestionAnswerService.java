package com.innowise.qaproxy.service;

import com.innowise.qaproxy.model.Question;
import com.innowise.qaproxy.model.SourceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Конфигурация источников данных для вопросов и ответов.
 * Загружается из внешнего YAML-файла config/sources.yaml
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionAnswerService {

    /**
     * Карта источников, где ключ — уникальный идентификатор источника.
     * значение - сама конфигурация
     */
    private Map<String, SourceConfig.SourceInfo> sources;

    // Список мапок, а каждой мапке ключь - это уникальный ID вопроса, а значение - сам объект с вопрос-ответом
    @Value("${sources.config.path:/config/sources.example.yaml}")
    private List<Map<String, Question>> questionAnswers;
}
