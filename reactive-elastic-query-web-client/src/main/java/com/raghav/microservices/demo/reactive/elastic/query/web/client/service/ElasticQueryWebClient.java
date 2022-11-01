package com.raghav.microservices.demo.reactive.elastic.query.web.client.service;

import com.raghav.microservices.demo.elastic.query.web.client.common.model.ElasticQueryWebClientRequestModel;
import com.raghav.microservices.demo.elastic.query.web.client.common.model.ElasticQueryWebClientResponseModel;
import reactor.core.publisher.Flux;

public interface ElasticQueryWebClient {
    Flux<ElasticQueryWebClientResponseModel> getDataByText(ElasticQueryWebClientRequestModel requestModel);
}
