package com.raghav.microservices.demo.elastic.query.client.util.service.impl;

import com.raghav.microservices.demo.common.util.CollectionsUtil;
import com.raghav.microservices.demo.elastic.model.index.impl.TwitterIndexModel;
import com.raghav.microservices.demo.elastic.query.client.util.exception.ElasticQueryClientException;
import com.raghav.microservices.demo.elastic.query.client.util.repository.TwitterElasticSearchQueryRepository;
import com.raghav.microservices.demo.elastic.query.client.util.service.ElasticQueryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class TwitterElasticQueryRepositoryClient implements ElasticQueryClient<TwitterIndexModel> {
    private final static Logger log = LoggerFactory.getLogger(TwitterElasticQueryRepositoryClient.class);

    private final TwitterElasticSearchQueryRepository twitterElasticSearchQueryRepository;

    public TwitterElasticQueryRepositoryClient(TwitterElasticSearchQueryRepository twitterElasticSearchQueryRepository) {
        this.twitterElasticSearchQueryRepository = twitterElasticSearchQueryRepository;
    }

    @Override
    public TwitterIndexModel getIndexModelById(String id) {
        Optional<TwitterIndexModel> searchResult = twitterElasticSearchQueryRepository.findById(id);
        log.info("Document with id {} retrieved successfully",
                searchResult.orElseThrow(() ->
                        new ElasticQueryClientException("No document found at elasticsearch with id " + id)).getId());
        return searchResult.get();
    }

    @Override
    public List<TwitterIndexModel> getIndexModelsByText(String text) {
        List<TwitterIndexModel> searchResult = twitterElasticSearchQueryRepository.findByText(text);
        log.info("{} of documents with text {} retrieved successfully", searchResult.size(), text);
        return searchResult;
    }

    @Override
    public List<TwitterIndexModel> getAllIndexModels() {
        List<TwitterIndexModel> searchResult = CollectionsUtil.getInstance().getListFromIterable(twitterElasticSearchQueryRepository.findAll());
        log.info("{} number of documents retrieved successfully", searchResult.size());
        return searchResult;
    }
}
