package com.inventarysystem.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.inventarysystem.models.ResponseGeneric;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseGeneric<String>> handleGlobalException(Exception ex, WebRequest request) {
        ResponseGeneric<String> res = new ResponseGeneric<>(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseGeneric<Map<String, String>>> handleBadRequest(MethodArgumentNotValidException ex) {
        Map<String, String> errorsByField = new HashMap<>();

        for(FieldError field : ex.getBindingResult().getFieldErrors()) {
            errorsByField.put(field.getField(), field.getDefaultMessage());
        }

        ResponseGeneric<Map<String, String>> res = new ResponseGeneric<>(HttpStatus.BAD_REQUEST, ex.getMessage(), errorsByField);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseGeneric<String>> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ResponseGeneric<String> res = new ResponseGeneric<>(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
}
