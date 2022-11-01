package com.raghav.microservices.demo.elastic.query.service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElasticQueryServiceWordCountResponseModel {
    private String word;
    private Long wordCount;
}
