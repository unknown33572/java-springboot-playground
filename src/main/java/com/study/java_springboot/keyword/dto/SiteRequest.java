package com.study.java_springboot.keyword.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SiteRequest {
    private String name;
    private String url;
    private String description;
    private Boolean active;
}
