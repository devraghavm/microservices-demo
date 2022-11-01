package com.raghav.microservices.demo.elastic.query.web.client.common.exception;

public class ElasticQueryWebClientException extends RuntimeException {
    public ElasticQueryWebClientException() {
        super();
    }

    public ElasticQueryWebClientException(String message) {
        super(message);
    }

    public ElasticQueryWebClientException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
