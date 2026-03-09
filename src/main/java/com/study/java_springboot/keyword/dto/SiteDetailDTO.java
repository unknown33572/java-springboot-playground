package com.study.java_springboot.keyword.dto;

import com.study.java_springboot.keyword.domain.Site;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class SiteDetailDTO {
    private Long id;
    private String name;
    private String url;
    private String description;
    private boolean active;
    private LocalDateTime createdAt;
    private List<KeywordDTO> keywords;

    public static SiteDetailDTO from(Site site) {
        List<KeywordDTO> keywordDTOs = site.getKeywords().stream()
                .map(k -> KeywordDTO.from(k, site.getName()))
                .toList();
        return new SiteDetailDTO(
                site.getId(), site.getName(), site.getUrl(),
                site.getDescription(), site.isActive(), site.getCreatedAt(),
                keywordDTOs
        );
    }
}
