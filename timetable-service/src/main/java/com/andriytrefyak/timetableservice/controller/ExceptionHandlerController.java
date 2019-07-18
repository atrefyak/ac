package com.andriytrefyak.timetableservice.controller;

import com.andriytrefyak.timetableservice.exception.RequestedDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RequestedDataNotFoundException.class)
    public ResponseEntity<String> handleDataNotFound(final RequestedDataNotFoundException dataNotFoundExc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundExc.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    private ResponseEntity<String> handleOtherExceptions(final NumberFormatException exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
    }

}
