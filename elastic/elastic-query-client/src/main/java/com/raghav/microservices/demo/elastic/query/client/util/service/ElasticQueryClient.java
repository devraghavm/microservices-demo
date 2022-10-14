package com.raghav.microservices.demo.elastic.query.client.util.service;

import com.raghav.microservices.demo.elastic.model.index.IndexModel;

import java.util.List;

public interface ElasticQueryClient<T extends IndexModel> {
    T getIndexModelById(String id);

    List<T> getIndexModelsByText(String text);

    List<T> getAllIndexModels();
}
