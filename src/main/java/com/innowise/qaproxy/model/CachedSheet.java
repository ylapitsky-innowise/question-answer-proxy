package com.innowise.qaproxy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

/**
 * Компактное представление диапазона Google Sheets в кэше.
 * <p>
 * Заголовки хранятся один раз на весь лист, строки — как {@code String[]},
 * чтобы минимизировать количество объектов в куче JVM.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CachedSheet {

    /** Уникальный идентификатор листа (совпадает с {@code listId} из конфигурации). */
    private String listId;

    /** Версия данных в Google Sheets на момент синхронизации. */
    private long version;

    /** Момент успешной синхронизации. */
    private Instant syncedAt;

    /** Имена колонок — один раз на весь лист. */
    private List<String> headers;

    /** Каждая строка — массив значений в порядке {@link #headers}. */
    private List<String[]> rows;


    /** Удобный доступ к количеству строк. */
    public int rowCount() {
        return rows == null ? 0 : rows.size();
    }

    /** Иммутабельные пустые заголовки — чтобы не возвращать {@code null}. */
    public List<String> headersOrEmpty() {
        return headers == null ? Collections.emptyList() : headers;
    }

    /** Иммутабельные пустые строки — чтобы не возвращать {@code null}. */
    public List<String[]> rowsOrEmpty() {
        return rows == null ? Collections.emptyList() : rows;
    }
}