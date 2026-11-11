package com.innowise.qaproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.File;

@SpringBootApplication
@EnableCaching   // ← включить Spring Cache
public class QuestionAnswerProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionAnswerProxyApplication.class, args);

//        // Временная отладка
//        System.out.println("=== DEBUG INFO ===");
//        System.out.println("Working dir: " + System.getProperty("user.dir"));
//        System.out.println("Config abs path: " + new File("./config/sources.yaml").getAbsolutePath());
//        System.out.println("Config exists: " + new File("./config/sources.yaml").exists());
//        System.out.println("==================");
//
//        SpringApplication.run(QuestionAnswerProxyApplication.class, args);
	}

}
