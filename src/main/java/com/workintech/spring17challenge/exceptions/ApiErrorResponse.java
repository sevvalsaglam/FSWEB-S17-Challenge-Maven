package com.workintech.spring17challenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {
    private Integer status;
    private String message;
    private Long timestamp;

    public ApiErrorResponse(String message) {
    }


    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
