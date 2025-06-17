package com.example.hethongthongtin.exception;

import lombok.Data;
import lombok.Getter;

@Data
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;


    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
