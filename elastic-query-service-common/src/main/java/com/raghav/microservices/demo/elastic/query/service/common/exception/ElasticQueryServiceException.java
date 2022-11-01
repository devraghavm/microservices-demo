package com.raghav.microservices.demo.elastic.query.service.common.exception;

public class ElasticQueryServiceException extends RuntimeException {
    public ElasticQueryServiceException() {
        super();
    }

    public ElasticQueryServiceException(String message) {
        super(message);
    }

    public ElasticQueryServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
