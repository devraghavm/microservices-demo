package com.raghav.microservices.demo.analytics.service.business.impl;


import com.raghav.microservices.demo.analytics.service.business.AnalyticsService;
import com.raghav.microservices.demo.analytics.service.dataaccess.repository.AnalyticsRepository;
import com.raghav.microservices.demo.analytics.service.model.AnalyticsResponseModel;
import com.raghav.microservices.demo.analytics.service.transformer.EntityToResponseModelTransformer;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TwitterAnalyticsService implements AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    private final EntityToResponseModelTransformer entityToResponseModelTransformer;

    public TwitterAnalyticsService(AnalyticsRepository repository,
                                   EntityToResponseModelTransformer transformer) {
        this.analyticsRepository = repository;
        this.entityToResponseModelTransformer = transformer;
    }

    @Override
    public Optional<AnalyticsResponseModel> getWordAnalytics(String word) {
        return entityToResponseModelTransformer.getResponseModel(
                analyticsRepository.getAnalyticsEntitiesByWord(word, PageRequest.of(0, 1))
                                   .stream().findFirst().orElse(null));
    }
}
