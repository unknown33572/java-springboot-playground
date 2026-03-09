package com.study.java_springboot.keyword.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardSummaryDTO {
    private long totalSites;
    private long activeSites;
    private long totalKeywords;
    private int totalMentionsToday;
    private String topKeyword;
}
