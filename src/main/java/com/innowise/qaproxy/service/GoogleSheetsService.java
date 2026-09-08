package com.innowise.qaproxy.service;

import com.innowise.qaproxy.model.QuestionAnswer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GoogleSheetsService {

    List<QuestionAnswer> readQuestionsFromSheet(String listName, Pageable pageable);

    QuestionAnswer readQuestionFromSheet(String listName, String questionId);

    int getRowCount(String listName);

    String health(String listName);

    List<String> getAvailableLists();
}
