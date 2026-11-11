package com.innowise.qaproxy;

import com.google.api.services.sheets.v4.Sheets;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class QuestionAnswerProxyApplicationTests {

    @MockBean
    private Sheets sheetsService;   // ← заменяет реальный бин, GoogleSheetsConfig не создаётся

    @Test
    void contextLoads() {
    }
}