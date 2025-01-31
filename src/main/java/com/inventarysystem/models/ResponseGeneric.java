package com.inventarysystem.models;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseGeneric<T> {
    private String status;
    private Integer code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ResponseGeneric(HttpStatus status, String message, T data) {
        this.status = status.getReasonPhrase();
        this.code = status.value();
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}
