package com.innowise.qaproxy.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Утилиты для работы с GoogleSheets / таблицами
 */
@Slf4j
public final class GoogleSheetsUtils {


    // В Google Sheets ячейка: "2026-09-08 12:35:40"
    private static final DateTimeFormatter SHEETS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private GoogleSheetsUtils() {
        throw new UnsupportedOperationException("This is a utility class, and it cannot be instantiated.");
    }


    public LocalDateTime parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }

        try {
            return LocalDateTime.parse(dateStr, SHEETS_FORMATTER);
        } catch (DateTimeParseException e) {
            // fallback: пробуем другой формат
            try {
                return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
            } catch (DateTimeParseException e2) {
                log.warn("Unable to parse date: {}", dateStr);
                return null;
            }
        }
    }
}
