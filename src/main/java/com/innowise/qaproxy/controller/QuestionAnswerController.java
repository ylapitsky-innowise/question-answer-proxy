package com.innowise.qaproxy.controller;

import com.innowise.qaproxy.model.QuestionAnswer;
import com.innowise.qaproxy.service.GoogleSheetsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.innowise.qaproxy.constant.ApiConstants.*;


@Slf4j
@RestController
@RequestMapping(QUESTION_ANSWER_URL)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class QuestionAnswerController {

    private final GoogleSheetsService googleSheetsService;


    // ============================================================
    // 1. Получить все доступные списки (темы/категории)
    // ============================================================

    /**
     * Получить список всех доступных списков (тем/категорий) вопросов.
     * Пример: GET /api/v1/question-answers/lists
     * @return Список названий всех доступных списков
     */
    @GetMapping(LISTS)
    public ResponseEntity<List<String>> getAvailableLists() {
        log.info("📋 GET {}{} - fetching all available lists", QUESTION_ANSWER_URL, LISTS);
        return ResponseEntity.ok(googleSheetsService.getAvailableLists());
    }


    // ============================================================
    // 2. Получить все вопросы из указанного списка (с фильтрацией по списку и пагинацией)
    // ============================================================

    /**
     * Получить все вопросы из указанного списка с поддержкой пагинации и фильтрации по списку.
     * Пример: GET /api/v1/question-answers?page=0&size=20 → все вопросы (первые 20)
     * Пример: GET /api/v1/question-answers?list_name=spring → все вопросы из списка Spring
     * Пример: GET /api/v1/question-answers?list_name=spring&page=0&size=20 → первые 20 вопросов из списка Spring
     * @param listName Наименование списка вопросов (тема/категория) — ОБЯЗАТЕЛЬНЫЙ
     * @param pageable Параметры пагинации (page, size, sort)
     * @return Список вопросов из указанного списка
     */
    @GetMapping
    public ResponseEntity<List<QuestionAnswer>> getQuestions(
            @RequestParam(name = "list_name")
            @Valid
            @NotBlank(message = "Название списка не может быть пустым")
            @Size(max = 100, message = "Название списка не должно превышать 100 символов")
            String listName,
            @PageableDefault(size = 100, sort = "questionId")
            Pageable pageable
    ) {
        log.info("📖 GET {}?list_name={} - fetching questions from list: {}", QUESTION_ANSWER_URL, listName, listName);

        return ResponseEntity.ok(googleSheetsService.readQuestionsFromSheet(listName, pageable));
    }


    // ============================================================
    // 3. Получить конкретный вопрос по ID из указанного списка
    // ============================================================

    /**
     * Получить конкретный вопрос по его ID из указанного списка.
     * Пример: GET /api/v1/question-answers/Q001 → вопрос Q001<
     * Пример: GET /api/v1/question-answers/Q001?list_name=spring → вопрос Q001 из списка Spring
     * @param questionId ID вопроса (уникальный идентификатор) — ОБЯЗАТЕЛЬНЫЙ
     * @param listName   Наименование списка (тема/категория) — ОБЯЗАТЕЛЬНЫЙ
     * @return Вопрос с указанным ID из указанного списка
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionAnswer> getQuestionById(
            @PathVariable
            @Valid
            @NotBlank(message = "ID вопроса не может быть пустым")
            @Size(max = 50, message = "ID вопроса не должен превышать 50 символов")
            String questionId,
            @RequestParam(name = "list_name")
            @Valid
            @NotBlank(message = "Название списка не может быть пустым")
            @Size(max = 100, message = "Название списка не должно превышать 100 символов")
            String listName
    ) {
        log.info("🔍 GET {}/{}?list_name={} - fetching question by id from list",
                QUESTION_ANSWER_URL, questionId, listName);

        QuestionAnswer question = googleSheetsService.readQuestionFromSheet(questionId, listName);

        if (question == null) {
            log.warn("⚠️ Question with id '{}' not found in list '{}'", questionId, listName);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(question);
    }


    // ============================================================
    // 4. Получить количество вопросов
    // ============================================================

    /**
     * Получить общее количество вопросов в указанном списке.
     * Пример: GET /api/v1/question-answers/count?list_name=spring → количество вопросов в списке Spring
     * @param listName Наименование списка (тема/категория) — ОБЯЗАТЕЛЬНЫЙ
     * @return Количество вопросов в указанном списке
     */
    @GetMapping(COUNT)
    public ResponseEntity<Integer> getCount(
            @RequestParam(name = "list_name")
            @Valid
            @NotBlank(message = "Название списка не может быть пустым")
            @Size(max = 100, message = "Название списка не должно превышать 100 символов")
            String listName
    ) {
        log.info("📊 GET {}{}?list_name={} - fetching count from list",
                QUESTION_ANSWER_URL, COUNT, listName);

        return ResponseEntity.ok(googleSheetsService.getRowCount(listName));
    }


    // ============================================================
    // 5. Проверка здоровья (доступность списка)
    // ============================================================

    /**
     * Проверить доступность и работоспособность указанного списка вопросов.
     * Выполняет проверку подключения к Google Sheets и доступности указанного списка.
     * Пример: GET /api/v1/question-answers/health?list_name=spring → проверка доступности списка Spring
     * @param listName Наименование списка (тема/категория) — ОБЯЗАТЕЛЬНЫЙ
     * @return "OK", если список доступен, иначе сообщение об ошибке
     */
    @GetMapping(HEALTH)
    public ResponseEntity<String> health(
            @RequestParam(name = "list_name")
            @Valid
            @NotBlank(message = "Название списка не может быть пустым")
            @Size(max = 100, message = "Название списка не должно превышать 100 символов")
            String listName
    ) {
        log.info("💚 GET {}{}?list_name={} - health check for list",
                QUESTION_ANSWER_URL, HEALTH, listName);

        try {
            String result = googleSheetsService.health(listName);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("❌ Health check failed for list '{}': {}", listName, e.getMessage());
            return ResponseEntity.status(503).body("Service unavailable: " + e.getMessage());
        }
    }
}
