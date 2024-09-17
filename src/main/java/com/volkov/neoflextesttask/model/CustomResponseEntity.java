package com.volkov.neoflextesttask.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponseEntity<T> extends ResponseEntity<T> {

    public CustomResponseEntity(T body, HttpHeaders headers, HttpStatus status) {
        super(body, headers, status);
    }

    @Override
    public String toString() {
        StringBuilder responseBuilder = new StringBuilder();

        // Форматируем строку ответа
        responseBuilder.append("HTTP/1.1 ")
                .append(getStatusCodeValue())
                .append(" ")
                .append(getStatusCode().getReasonPhrase())
                .append("\r\n");

        // Добавляем заголовки
        getHeaders().forEach((key, value) ->
                responseBuilder.append(key).append(": ").append(String.join(", ", value)).append("\r\n")
        );

        // Пустая строка перед телом ответа
        responseBuilder.append("\r\n");

        // Добавляем тело ответа
        if (getBody() != null) {
            responseBuilder.append(getBody().toString());
        }

        return responseBuilder.toString();
    }
}