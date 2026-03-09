package com.study.java_springboot.keyword.controller;

import com.study.java_springboot.keyword.dto.SiteDTO;
import com.study.java_springboot.keyword.dto.SiteDetailDTO;
import com.study.java_springboot.keyword.dto.SiteRequest;
import com.study.java_springboot.keyword.service.KeywordMonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {

    private final KeywordMonitorService service;

    @GetMapping
    public List<SiteDTO> list() {
        return service.getAllSites();
    }

    @GetMapping("/{id}")
    public SiteDetailDTO detail(@PathVariable Long id) {
        return service.getSiteDetail(id);
    }

    @PostMapping
    public ResponseEntity<SiteDTO> create(@RequestBody SiteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createSite(request));
    }

    @PutMapping("/{id}")
    public SiteDTO update(@PathVariable Long id, @RequestBody SiteRequest request) {
        return service.updateSite(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteSite(id);
        return ResponseEntity.noContent().build();
    }
}
