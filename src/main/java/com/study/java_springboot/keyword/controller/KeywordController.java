package com.study.java_springboot.keyword.controller;

import com.study.java_springboot.keyword.dto.KeywordDTO;
import com.study.java_springboot.keyword.dto.KeywordStatDTO;
import com.study.java_springboot.keyword.service.KeywordMonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordMonitorService service;

    @GetMapping("/top")
    public List<KeywordDTO> topKeywords(@RequestParam(defaultValue = "10") int limit) {
        return service.getTopKeywords(limit);
    }

    @GetMapping("/{id}/stats")
    public List<KeywordStatDTO> stats(@PathVariable Long id,
                                       @RequestParam(defaultValue = "30") int days) {
        return service.getKeywordStats(id, days);
    }

    @GetMapping("/site/{siteId}")
    public List<KeywordDTO> bySite(@PathVariable Long siteId) {
        return service.getKeywordsForSite(siteId);
    }
}
