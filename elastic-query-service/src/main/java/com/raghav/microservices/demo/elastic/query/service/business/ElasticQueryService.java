package com.raghav.microservices.demo.elastic.query.service.business;

import com.raghav.microservices.demo.elastic.query.service.common.model.ElasticQueryServiceResponseModel;
import com.raghav.microservices.demo.elastic.query.service.model.ElasticQueryServiceAnalyticsResponseModel;

import java.util.List;

public interface ElasticQueryService {
    ElasticQueryServiceResponseModel getDocumentById(String id);

    ElasticQueryServiceAnalyticsResponseModel getDocumentByText(String text, String accessToken);

    List<ElasticQueryServiceResponseModel> getAllDocuments();
}
