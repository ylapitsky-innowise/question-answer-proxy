package com.innowise.qaproxy.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionAnswer {

    @NotBlank(message = "ID вопроса обязателен")
    @Size(max = 20, message = "ID вопроса не должен превышать 20 символов")
    private String questionId; // ID вопроса в базовой БД (неизменен)   - ОБЯЗАТЕЛЬНЫЙ!

    @NotBlank(message = "Формулировка вопроса обязательна")
    @Size(max = 500, message = "Формулировка не должна превышать 500 символов")
    private String question; // Формулировка вопроса                    - ОБЯЗАТЕЛЬНЫЙ!

    @NotBlank(message = "Ответ обязателен")
    @Size(max = 2000, message = "Ответ не должен превышать 2000 символов")
    private String answer; // Вариант правильного ответа                - ОБЯЗАТЕЛЬНЫЙ!

    // Версия данных (для кэширования)
    private Long version;

    // Метаданные
    @Size(max = 50, message = "Тема/ раздел вопроса не должен превышать 50 символов")
    private String topic; // Тема вопроса (раздел)

    @Size(max = 10, message = "Уровень сложности вопроса вопроса не должен превышать 10 символов")
    private String difficulty; // Уровень сложности вопроса (3 уровня)

}
