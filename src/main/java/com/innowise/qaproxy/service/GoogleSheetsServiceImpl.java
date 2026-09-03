package com.innowise.qaproxy.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.innowise.qaproxy.model.QuestionAnswer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private final Sheets sheetsService;

    @Value("${google.sheets.spreadsheet-id}")
    private String spreadsheetId;

    @Value("${google.sheets.range:question_answer}")
    private String range;

    @Override
    public List<QuestionAnswer> readQuestionsFromSheet() {
        try {
            log.info("Reading data from spreadsheet: {}", spreadsheetId);

            ValueRange response = sheetsService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();

            List<List<Object>> values = response.getValues();

            if (values == null || values.isEmpty()) {
                log.warn("No data found in the specified range");
                return Collections.emptyList();
            }

            log.info("Found {} rows of data", values.size());

            List<QuestionAnswer> questions = new ArrayList<>();
            for (int i = 1; i < values.size(); i++) {
                List<Object> row = values.get(i);
                QuestionAnswer question = mapRowToQuestion(row);
                if (question != null) {
                    questions.add(question);
                }
            }

            log.info("Successfully parsed {} questions", questions.size());
            return questions;

        } catch (IOException e) {
            log.error("Error reading data from Google Sheets", e);
            throw new RuntimeException("Failed to read data from Google Sheets", e);
        }
    }

    @Override
    public int getRowCount() {
        try {
            ValueRange response = sheetsService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();

            List<List<Object>> values = response.getValues();
            return values != null ? values.size() - 1 : 0;

        } catch (IOException e) {
            log.error("Error getting row count", e);
            return 0;
        }
    }

    private QuestionAnswer mapRowToQuestion(List<Object> row) {
        try {
            return QuestionAnswer.builder()
                    .questionId(getStringValue(row, 0))
                    .question(getStringValue(row, 1))
                    .answer(getStringValue(row, 2))
                    .build();
        } catch (Exception e) {
            log.warn("Error parsing row: {}", e.getMessage());
            return null;
        }
    }

    private String getStringValue(List<Object> row, int index) {
        if (row == null || index >= row.size()) {
            return null;
        }
        Object value = row.get(index);
        return value != null ? value.toString().trim() : null;
    }
}
