package com.miktl.forum.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity emptyFields(MethodArgumentNotValidException exception) {
        List<ValidationErrorData> errors = exception.getFieldErrors()
                .stream()
                .map(ValidationErrorData::new)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
