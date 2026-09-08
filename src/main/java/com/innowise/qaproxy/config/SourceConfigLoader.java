package com.innowise.qaproxy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.qaproxy.model.SourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class SourceConfigLoader {

    private SourceConfig config;

    @Value("${sources.config.path:./config/sources.yaml}")
    private String configPath;

    @PostConstruct
    public void loadConfig() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Поддержка YAML
            mapper.findAndRegisterModules();

            // Проверяем внешний файл
            Path path = Paths.get(configPath);
            if (Files.exists(path)) {
                config = mapper.readValue(path.toFile(), SourceConfig.class);
                // загружаем из внешнего файла
                log.info("✅ Loaded {} sources from {}", config.getSources().size(), configPath);
            } else {
                log.warn("⚠️ Config file not found at {}, using defaults", configPath);
                // пробуем загрузить из resources
                loadDefaults();
            }
        } catch (IOException e) {
            log.error("Failed to load source config: {}", e.getMessage());
            loadDefaults();
        }
    }

    private void loadDefaults() {
        // Fallback для разработки (только для тестов!)
        SourceConfig defaults = new SourceConfig();
        // ... заполнить дефолтными значениями
        this.config = defaults;
    }

    public SourceConfig getConfig() {
        return config;
    }

    public SourceConfig.SourceInfo getSource(String name) {
        return config.getSources().get(name);
    }
}
