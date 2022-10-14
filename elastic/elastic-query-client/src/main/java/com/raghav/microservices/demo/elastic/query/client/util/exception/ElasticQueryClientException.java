package com.raghav.microservices.demo.elastic.query.client.util.exception;

public class ElasticQueryClientException extends RuntimeException {
    public ElasticQueryClientException() {
        super();
    }

    public ElasticQueryClientException(String message) {
        super(message);
    }

    public ElasticQueryClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
