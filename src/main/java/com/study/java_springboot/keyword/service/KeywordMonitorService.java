package com.study.java_springboot.keyword.service;

import com.study.java_springboot.keyword.domain.Keyword;
import com.study.java_springboot.keyword.domain.KeywordStat;
import com.study.java_springboot.keyword.domain.Site;
import com.study.java_springboot.keyword.dto.*;
import com.study.java_springboot.keyword.repository.KeywordRepository;
import com.study.java_springboot.keyword.repository.KeywordStatRepository;
import com.study.java_springboot.keyword.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeywordMonitorService {

    private final SiteRepository siteRepository;
    private final KeywordRepository keywordRepository;
    private final KeywordStatRepository keywordStatRepository;

    // === Site ===

    public List<SiteDTO> getAllSites() {
        return siteRepository.findAll().stream()
                .map(SiteDTO::from)
                .toList();
    }

    public SiteDetailDTO getSiteDetail(Long id) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Site not found: " + id));
        return SiteDetailDTO.from(site);
    }

    @Transactional
    public SiteDTO createSite(SiteRequest request) {
        Site site = new Site(request.getName(), request.getUrl(), request.getDescription());
        siteRepository.save(site);
        return SiteDTO.from(site);
    }

    @Transactional
    public SiteDTO updateSite(Long id, SiteRequest request) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Site not found: " + id));
        if (request.getName() != null) site.setName(request.getName());
        if (request.getUrl() != null) site.setUrl(request.getUrl());
        if (request.getDescription() != null) site.setDescription(request.getDescription());
        if (request.getActive() != null) site.setActive(request.getActive());
        return SiteDTO.from(site);
    }

    @Transactional
    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }

    // === Keyword ===

    public List<KeywordDTO> getKeywordsForSite(Long siteId) {
        Site site = siteRepository.findById(siteId)
                .orElseThrow(() -> new IllegalArgumentException("Site not found: " + siteId));
        return keywordRepository.findBySiteId(siteId).stream()
                .map(k -> KeywordDTO.from(k, site.getName()))
                .toList();
    }

    public List<KeywordDTO> getTopKeywords(int limit) {
        return keywordRepository.findTop10ByOrderByTotalMentionsDesc().stream()
                .limit(limit)
                .map(k -> KeywordDTO.from(k, k.getSite().getName()))
                .toList();
    }

    // === Stats ===

    public List<KeywordStatDTO> getKeywordStats(Long keywordId, int days) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(days);
        return keywordStatRepository
                .findByKeywordIdAndRecordDateBetweenOrderByRecordDateAsc(keywordId, start, end)
                .stream()
                .map(KeywordStatDTO::from)
                .toList();
    }

    // === Dashboard ===

    public DashboardSummaryDTO getDashboardSummary() {
        long totalSites = siteRepository.count();
        long activeSites = siteRepository.countByActiveTrue();
        long totalKeywords = keywordRepository.count();
        int mentionsToday = keywordStatRepository.sumMentionCountByDate(LocalDate.now());

        List<Keyword> topKeywords = keywordRepository.findTop10ByOrderByTotalMentionsDesc();
        String topKeyword = topKeywords.isEmpty() ? "-" : topKeywords.get(0).getWord();

        return new DashboardSummaryDTO(totalSites, activeSites, totalKeywords, mentionsToday, topKeyword);
    }

    public Map<String, List<KeywordStatDTO>> getTrendData(List<Long> keywordIds, int days) {
        Map<String, List<KeywordStatDTO>> result = new LinkedHashMap<>();
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(days);

        for (Long keywordId : keywordIds) {
            Keyword keyword = keywordRepository.findById(keywordId).orElse(null);
            if (keyword == null) continue;

            List<KeywordStatDTO> stats = keywordStatRepository
                    .findByKeywordIdAndRecordDateBetweenOrderByRecordDateAsc(keywordId, start, end)
                    .stream()
                    .map(KeywordStatDTO::from)
                    .toList();
            result.put(keyword.getWord(), stats);
        }
        return result;
    }
}
