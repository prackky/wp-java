package org.knowshipp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BlogPost (
    String title,
    String content,
    String status,
    List<String> tags,
    String excerpt,
    Map<String, Object> meta,
    @JsonProperty("yoast_head_json")
    YoastHeadJson yoastHeadJson
) {}
