package com.raghav.microservices.demo.elastic.query.web.client.service;


import com.raghav.microservices.demo.elastic.query.web.client.common.model.ElasticQueryWebClientAnalyticsResponseModel;
import com.raghav.microservices.demo.elastic.query.web.client.common.model.ElasticQueryWebClientRequestModel;

public interface ElasticQueryWebClient {
    ElasticQueryWebClientAnalyticsResponseModel getDataByText(ElasticQueryWebClientRequestModel requestModel);
}
