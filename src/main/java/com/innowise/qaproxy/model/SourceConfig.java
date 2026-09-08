package com.innowise.qaproxy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * Конфигурация источников данных для вопросов и ответов.
 * Система поддерживает множество источников (списков вопросов),
 * каждый из которых представляет собой отдельную Google таблицу или диапазон.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SourceConfig {

    // ключ — уникальный идентификатор источника / значение - сама конфигурация
    private Map<String, SourceInfo> sources;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class SourceInfo {

        // ============================================================
        // Идентификация
        // ============================================================
        private String id; // id в системе
        private String spreadsheetId; // Идентификатор Google Sheets таблицы (часть URL), откуда берем диапазон
        private String rangeName; // Имя именованного диапазона в Google Sheets.

        private String sourcePageLink; // Ссылка на саму страницу с таблицей

        // ============================================================
        // Отображение
        // ============================================================
        private String tableName; // Уникальное имя источника для обращения в API.
        private String displayName; // Человеко-читаемое отображаемое имя источника.
        private String description; // Краткое описание содержимого источника.

        // ============================================================
        // Версионность
        // ============================================================
        private Long version = 0L; // Текущая версия данных в Google Sheets.

        // ============================================================
        // Метаданные
        // ============================================================
        private String authorName; // Имя автора или владельца источника данных.
        private String authorContact; // Контактные данные для связи с автором (email, telegram, и т.д.).

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdAt; // Дата создания саиого источника диапазона/ таблицы

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime updatedAt; // Дата последней модификации данных саиого источника диапазона/ таблицы

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastSyncedAt; // Дата и время последней успешной синхронизации с Google Sheets.

        // ============================================================
        // Статус
        // ============================================================
        private Boolean isActive = true; // Статус доступности источника для пользователей.
        private Boolean isPublic = true; // Флаг публичности источника. Приватные/ публичные списки
        private Integer priority = 0; // Приоритет отображения источника (для сортировки списка источников в UI).

        private String lastSyncStatus = "NEVER"; // Статус последней синхронизации (SUCCESS, FAILED, IN_PROGRESS, NEVER)
        private String syncErrorMessage; // Сообщение об ошибке при неудачной синхронизации.

        // ============================================================
        // Фильтрация
        // ============================================================
        private List<String> tags; // Список тегов для категоризации источника.
        private List<String> topics; // Список тем, представленных в данном источнике ("IoC/DI", "AOP", "Security"...).

        // ============================================================
        // Статистика
        // ============================================================
        private Integer questionCount = 0; // Количество вопросов в источнике.

    }
}
