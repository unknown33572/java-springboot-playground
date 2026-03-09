package com.study.java_springboot.keyword.repository;

import com.study.java_springboot.keyword.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findBySiteId(Long siteId);
    List<Keyword> findTop10ByOrderByTotalMentionsDesc();
}
