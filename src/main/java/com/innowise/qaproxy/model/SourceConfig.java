package com.innowise.qaproxy.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SourceConfig {
    private Map<String, SourceInfo> sources;

    @Data
    public static class SourceInfo {
        private String name;
        private String description;
        private String spreadsheetId;
        private String range;
        private Boolean enabled;
        private Integer priority;
        private List<String> tags;
    }
}