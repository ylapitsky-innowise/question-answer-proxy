package com.innowise.qaproxy.service;

import com.innowise.qaproxy.config.SourceConfigLoader;
import com.innowise.qaproxy.model.Question;
import com.innowise.qaproxy.model.SourceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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


    private final SourceConfigLoader configLoader;

    /**
     * Получить все источники
     */
    public Map<String, SourceConfig.SourceInfo> getAllSources() {
        return configLoader.getConfig().getSources();
    }

    /**
     * Получить конкретный источник
     */
    public SourceConfig.SourceInfo getSource(String listName) {
        return configLoader.getConfig().getSources().get(listName);
    }

    /**
     * Получить список имен доступных источников
     */
    public List<String> getAvailableListNames() {
        return configLoader.getConfig().getSources().entrySet().stream()
                .filter(e -> e.getValue().getIsActive())
                .sorted(Comparator.comparing(e -> e.getValue().getPriority()))
                .map(Map.Entry::getKey)
                .toList();
    }

    ///

//    /**
//     * Карта источников, где ключ — уникальный идентификатор источника.
//     * значение - сама конфигурация
//     */
//    private Map<String, SourceConfig.SourceInfo> sources;
//
//    // Список мапок, а каждой мапке ключ - это уникальный ID вопроса, а значение - сам объект с вопрос-ответом
//    @Value("${sources.config.path:/config/sources.example.yaml}")
//    private List<Map<String, Question>> questionAnswers;
}
