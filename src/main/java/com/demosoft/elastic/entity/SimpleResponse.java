package com.demosoft.elastic.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleResponse {

    private String message;
}
