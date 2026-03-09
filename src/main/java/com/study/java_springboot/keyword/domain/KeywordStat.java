package com.study.java_springboot.keyword.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "keyword_stats")
@Getter @Setter
@NoArgsConstructor
public class KeywordStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate recordDate;

    private int mentionCount;

    private double sentimentScore;

    private int searchVolume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    public KeywordStat(LocalDate recordDate, int mentionCount, double sentimentScore, int searchVolume, Keyword keyword) {
        this.recordDate = recordDate;
        this.mentionCount = mentionCount;
        this.sentimentScore = sentimentScore;
        this.searchVolume = searchVolume;
        this.keyword = keyword;
    }
}
