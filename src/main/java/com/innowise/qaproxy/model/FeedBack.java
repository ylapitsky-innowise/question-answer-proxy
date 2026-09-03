package com.innowise.qaproxy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


/**
 * Сущность ФИТБЭК, включает вопрос с ответом + оценку интервьюера
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedBack extends QuestionAnswer {

    @DateTimeFormat(pattern = "yyyy-MM-dd")        // для Spring
    @JsonFormat(pattern = "yyyy-MM-dd")            // для JSON (Jackson)
    @NotNull(message = "Дата собеседования обязательна")
    private LocalDate interviewDate; // Дата проведения собеседования          - ОБЯЗАТЕЛЬНЫЙ!

    @NotBlank(message = "'Оценка ответа' является обязательным полем")
    @Size(max = 10, message = "Размер 'Оценка ответа' не должно превышать 10 символов")
    private String result; // Результат/ оценка ответа на данный вопрос   - ОБЯЗАТЕЛЬНЫЙ!

    @Size(max = 100, message = "Имя интервьюера не должно превышать 100 символов")
    private String interviewer; // Интервьюер, Кто был интервьюером             - НЕ ОБЯЗАТЕЛЬНЫЙ

    @Size(max = 500, message = "Текстовый комментарий не должен превышать 500 символов")
    private String feedback; // Текстовый комментарий
}
