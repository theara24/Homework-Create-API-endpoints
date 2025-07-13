package org.example.homeworkcreateendpoint.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ServiceException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleServiceExcepion(ResponseStatusException e){
        ErrorResponse<String> error = ErrorResponse.<String>builder()
                .message("Business Logic Error")
                .status(e.getStatusCode().value())
                .timestamp(LocalDateTime.now())
                .details(e.getReason())
                .build();

        return ResponseEntity
                .status(e.getStatusCode().value())
                .body(error);

    }

}
