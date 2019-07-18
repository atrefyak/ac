package com.andriytrefyak.searchrouteservice.controller;

import com.andriytrefyak.searchrouteservice.enumaration.ErrorCodeEnum;
import com.andriytrefyak.searchrouteservice.exception.RouteNotFoundException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;

@ControllerAdvice
public class GlobalControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleIntegrationException(final FeignException exc) {
        LOGGER.error("Integration error", exc);
        return ResponseEntity.status(exc.status()).body(ErrorCodeEnum.getErrorMessage(exc.status()));
    }

    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<String> handleRouteNotFoundException(final RouteNotFoundException exc) {
        LOGGER.error("Route is not found", exc);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParameterException(final MissingServletRequestParameterException exc) {
        LOGGER.warn("Missing requested parameter error", exc);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage()+ ". " + ErrorCodeEnum.getErrorMessage(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handledArgumentException(final MethodArgumentTypeMismatchException exc) {
        LOGGER.warn("Type parameter error", exc);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage()+ ". " + ErrorCodeEnum.getErrorMessage(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnotherException(final Exception exc) {
        LOGGER.error("Something went wrong", exc);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorCodeEnum.getErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler
    public ResponseEntity<String> handleValidationException(final ValidationException exc) {
        LOGGER.error("Invalid parameters", exc);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage()+ ". " + ErrorCodeEnum.getErrorMessage(HttpStatus.BAD_REQUEST.value()));
    }

}
