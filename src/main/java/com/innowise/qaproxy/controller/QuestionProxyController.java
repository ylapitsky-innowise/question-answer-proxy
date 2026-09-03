package com.innowise.qaproxy.controller;

import com.innowise.qaproxy.model.QuestionAnswer;
import com.innowise.qaproxy.service.GoogleSheetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QuestionProxyController {

    private final GoogleSheetsService googleSheetsService;

    @GetMapping
    public List<QuestionAnswer> getQuestions() {
        log.info("GET /api/questions - fetching all questions");
        return googleSheetsService.readQuestionsFromSheet();
    }

    @GetMapping("/count")
    public int getCount() {
        log.info("GET /api/questions/count - fetching count");
        return googleSheetsService.getRowCount();
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
