package com.study.java_springboot.keyword.dto;

import com.study.java_springboot.keyword.domain.Keyword;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeywordDTO {
    private Long id;
    private String word;
    private int totalMentions;
    private double relevanceScore;
    private String siteName;
    private Long siteId;

    public static KeywordDTO from(Keyword keyword, String siteName) {
        return new KeywordDTO(
                keyword.getId(), keyword.getWord(),
                keyword.getTotalMentions(), keyword.getRelevanceScore(),
                siteName, keyword.getSite().getId()
        );
    }
}
