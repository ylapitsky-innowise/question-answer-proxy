package com.innowise.qaproxy.service;

import com.innowise.qaproxy.model.QuestionAnswer;
import java.util.List;

public interface GoogleSheetsService {
    List<QuestionAnswer> readQuestionsFromSheet();
    int getRowCount();
}
