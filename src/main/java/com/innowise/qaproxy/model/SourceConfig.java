package com.innowise.qaproxy.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class SourceConfig {
    private Map<String, SourceInfo> sources;

    @Data
    public static class SourceInfo {
        private String spreadsheetId; // URL таблицы, откуда берем диапазон
        private String rangeName; // имя диапазона в Google - таблицах
        private String tableName; // имя таблицы для клиента (для обращения в запросах)
        private String displayName; // Имя для отображения клиенту (человеко - читаемое и понятное)
        private String description; // Примечания по данной таблице

        private String author; // Автор диапазона/ таблицы
        private LocalDateTime createdAt; // Дата создания диапазона/ таблицы
        private LocalDateTime updatedAt; // Дата последней модификации диапазона/ таблицы

        private Boolean enabled; // Включен/ активен, либо деактивирован
        private List<String> tags; // Тэги (либо список тем, отображенных в этом листе/ таблице)

//        private String range; //
//        private Integer priority;
    }
}