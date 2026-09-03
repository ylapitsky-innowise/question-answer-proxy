package com.innowise.qaproxy.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@Configuration
public class GoogleSheetsConfig {

    @Value("${google.credentials.json}")
    private String credentialsJson;

    @Value("${google.sheets.application-name:Question Proxy}")
    private String applicationName;

    @Bean
    public Sheets sheetsService() throws IOException, GeneralSecurityException {
        log.info("Initializing Google Sheets service");

        try {
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(new ByteArrayInputStream(credentialsJson.getBytes()))
                    .createScoped("https://www.googleapis.com/auth/spreadsheets.readonly");

            return new Sheets.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    new HttpCredentialsAdapter(credentials))
                    .setApplicationName(applicationName)
                    .build();
        } catch (Exception e) {
            log.error("Failed to initialize Google Sheets: {}", e.getMessage());
            throw e;
        }
    }
}
