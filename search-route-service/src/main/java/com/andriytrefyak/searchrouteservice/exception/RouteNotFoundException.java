package com.andriytrefyak.searchrouteservice.exception;

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(final String message) {
        super(message);
    }
}
