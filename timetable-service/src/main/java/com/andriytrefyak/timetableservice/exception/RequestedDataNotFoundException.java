package com.andriytrefyak.timetableservice.exception;

public class RequestedDataNotFoundException extends RuntimeException {
    public RequestedDataNotFoundException(final String message) {
        super(message);
    }
}
