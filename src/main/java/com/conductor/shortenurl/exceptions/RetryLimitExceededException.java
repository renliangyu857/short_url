package com.conductor.shortenurl.exceptions;

public class RetryLimitExceededException extends RuntimeException {
    public RetryLimitExceededException(String message) {
        super(message);
    }
}
