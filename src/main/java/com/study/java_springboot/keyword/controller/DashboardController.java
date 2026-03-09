package com.study.java_springboot.keyword.controller;

import com.study.java_springboot.keyword.dto.DashboardSummaryDTO;
import com.study.java_springboot.keyword.dto.KeywordStatDTO;
import com.study.java_springboot.keyword.service.KeywordMonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final KeywordMonitorService service;

    @GetMapping("/summary")
    public DashboardSummaryDTO summary() {
        return service.getDashboardSummary();
    }

    @GetMapping("/trend")
    public Map<String, List<KeywordStatDTO>> trend(
            @RequestParam List<Long> keywordIds,
            @RequestParam(defaultValue = "30") int days) {
        return service.getTrendData(keywordIds, days);
    }
}
