package com.study.java_springboot.keyword.dto;

import com.study.java_springboot.keyword.domain.KeywordStat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class KeywordStatDTO {
    private Long id;
    private LocalDate recordDate;
    private int mentionCount;
    private double sentimentScore;
    private int searchVolume;

    public static KeywordStatDTO from(KeywordStat stat) {
        return new KeywordStatDTO(
                stat.getId(), stat.getRecordDate(),
                stat.getMentionCount(), stat.getSentimentScore(),
                stat.getSearchVolume()
        );
    }
}
