package com.study.java_springboot.keyword.init;

import com.study.java_springboot.keyword.domain.Keyword;
import com.study.java_springboot.keyword.domain.KeywordStat;
import com.study.java_springboot.keyword.domain.Site;
import com.study.java_springboot.keyword.repository.KeywordRepository;
import com.study.java_springboot.keyword.repository.KeywordStatRepository;
import com.study.java_springboot.keyword.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SiteRepository siteRepository;
    private final KeywordRepository keywordRepository;
    private final KeywordStatRepository keywordStatRepository;

    @Override
    @Transactional
    public void run(String... args) {
        Random random = new Random(42);

        // Sites
        Site naver = createSite("Naver Blog", "https://blog.naver.com", "네이버 블로그 기술 트렌드");
        Site techCommunity = createSite("Tech Community", "https://community.tech.kr", "한국 기술 커뮤니티");
        Site github = createSite("GitHub Trending", "https://github.com/trending", "GitHub 트렌딩 저장소");
        Site stackoverflow = createSite("Stack Overflow", "https://stackoverflow.com", "개발자 Q&A 플랫폼");
        Site devto = createSite("Dev.to", "https://dev.to", "개발자 블로그 플랫폼");

        // Keywords per site
        createKeywordsWithStats(naver, random,
                "Spring Boot", "React", "ChatGPT", "Kubernetes", "TypeScript",
                "Docker", "AWS", "Next.js");

        createKeywordsWithStats(techCommunity, random,
                "Spring Boot", "Python", "ChatGPT", "Flutter", "PostgreSQL",
                "Redis", "Kafka", "GraphQL", "Rust");

        createKeywordsWithStats(github, random,
                "React", "TypeScript", "Rust", "Go", "Python",
                "Kubernetes", "Docker", "Terraform", "Next.js", "Svelte");

        createKeywordsWithStats(stackoverflow, random,
                "JavaScript", "Python", "Java", "TypeScript", "React",
                "Node.js", "Spring Boot", "Docker", "PostgreSQL");

        createKeywordsWithStats(devto, random,
                "ChatGPT", "React", "Next.js", "Tailwind CSS", "TypeScript",
                "Rust", "Go", "AWS", "Kubernetes", "Docker", "Svelte");
    }

    private Site createSite(String name, String url, String description) {
        Site site = new Site(name, url, description);
        return siteRepository.save(site);
    }

    private void createKeywordsWithStats(Site site, Random random, String... words) {
        for (String word : words) {
            Keyword keyword = new Keyword(word, site);

            int baseMentions = 30 + random.nextInt(70);
            double baseRelevance = 0.4 + random.nextDouble() * 0.5;
            keyword.setRelevanceScore(Math.round(baseRelevance * 100.0) / 100.0);

            // Generate 30 days of stats
            int totalMentions = 0;
            for (int day = 0; day < 30; day++) {
                LocalDate date = LocalDate.now().minusDays(29 - day);

                double wave = Math.sin(day * 0.3 + random.nextDouble()) * 20;
                int mentions = Math.max(5, (int) (baseMentions + wave + random.nextInt(15)));
                double sentiment = Math.max(-1.0, Math.min(1.0,
                        0.3 + Math.sin(day * 0.2) * 0.3 + (random.nextDouble() - 0.5) * 0.4));
                sentiment = Math.round(sentiment * 100.0) / 100.0;
                int volume = mentions * (3 + random.nextInt(5));

                KeywordStat stat = new KeywordStat(date, mentions, sentiment, volume, keyword);
                keyword.getStats().add(stat);
                totalMentions += mentions;
            }

            keyword.setTotalMentions(totalMentions);
            keywordRepository.save(keyword);
        }
    }
}
