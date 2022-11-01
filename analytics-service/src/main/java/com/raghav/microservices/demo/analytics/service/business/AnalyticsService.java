package com.raghav.microservices.demo.analytics.service.business;


import com.raghav.microservices.demo.analytics.service.model.AnalyticsResponseModel;

import java.util.Optional;

public interface AnalyticsService {

    Optional<AnalyticsResponseModel> getWordAnalytics(String word);
}

