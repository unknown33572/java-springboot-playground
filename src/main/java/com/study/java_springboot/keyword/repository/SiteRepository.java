package com.study.java_springboot.keyword.repository;

import com.study.java_springboot.keyword.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {
    long countByActiveTrue();
}
