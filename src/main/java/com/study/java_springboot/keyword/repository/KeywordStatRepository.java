package com.study.java_springboot.keyword.repository;

import com.study.java_springboot.keyword.domain.KeywordStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface KeywordStatRepository extends JpaRepository<KeywordStat, Long> {
    List<KeywordStat> findByKeywordIdAndRecordDateBetweenOrderByRecordDateAsc(
            Long keywordId, LocalDate start, LocalDate end);

    @Query("SELECT COALESCE(SUM(s.mentionCount), 0) FROM KeywordStat s WHERE s.recordDate = :date")
    int sumMentionCountByDate(@Param("date") LocalDate date);
}
