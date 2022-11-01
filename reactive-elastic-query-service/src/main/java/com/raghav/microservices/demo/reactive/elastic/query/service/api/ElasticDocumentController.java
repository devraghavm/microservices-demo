package com.raghav.microservices.demo.reactive.elastic.query.service.api;

import com.raghav.microservices.demo.elastic.query.service.common.model.ElasticQueryServiceRequestModel;
import com.raghav.microservices.demo.elastic.query.service.common.model.ElasticQueryServiceResponseModel;
import com.raghav.microservices.demo.reactive.elastic.query.service.business.ElasticQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/documents")
public class ElasticDocumentController {
    private static final Logger log = LoggerFactory.getLogger(ElasticDocumentController.class);

    private final ElasticQueryService elasticQueryService;

    public ElasticDocumentController(ElasticQueryService elasticQueryService) {
        this.elasticQueryService = elasticQueryService;
    }

    @PostMapping(value = "/get-doc-by-text",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ElasticQueryServiceResponseModel> getDocumentByText(
            @RequestBody @Valid ElasticQueryServiceRequestModel requestModel
    ) {
        Flux<ElasticQueryServiceResponseModel> response = elasticQueryService.getDocumentByText(requestModel.getText());
        response = response.log();
        log.info("Returning from query reactive service for text {}!", requestModel.getText());
        return response;
    }

}
