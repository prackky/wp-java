package org.knowshipp.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record YoastHeadJson (String title, String description, Robots robots) {}
