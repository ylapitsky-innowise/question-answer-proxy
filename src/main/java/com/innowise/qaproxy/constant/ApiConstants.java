package com.innowise.qaproxy.constant;

/**
 * Класс для хранения констант API.
 * Содержит базовые пути, эндпоинты и вспомогательные строки.
 */
public final class ApiConstants {
    private ApiConstants() {
        // Приватный конструктор запрещает создание экземпляров
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // ============================================================
    // Базовые пути
    // ============================================================
    public static final String BASE_URL = "/api/v1";
    public static final String QUESTION_ANSWER_URL = BASE_URL + "/question-answers";
    public static final String FEEDBACK_URL = BASE_URL + "/feedback";

    // ============================================================
    // Под-эндпоинты (Sub-endpoints)
    // ============================================================
    public static final String LISTS = "/lists";
    public static final String COUNT = "/count";
    public static final String HEALTH = "/health";

}
