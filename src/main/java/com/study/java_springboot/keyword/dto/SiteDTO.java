package com.study.java_springboot.keyword.dto;

import com.study.java_springboot.keyword.domain.Site;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SiteDTO {
    private Long id;
    private String name;
    private String url;
    private String description;
    private boolean active;
    private LocalDateTime createdAt;
    private int keywordCount;

    public static SiteDTO from(Site site) {
        return new SiteDTO(
                site.getId(), site.getName(), site.getUrl(),
                site.getDescription(), site.isActive(), site.getCreatedAt(),
                site.getKeywords().size()
        );
    }
}
