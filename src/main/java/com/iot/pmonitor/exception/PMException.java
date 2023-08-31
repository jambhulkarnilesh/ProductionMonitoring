package com.iot.pmonitor.exception;

import lombok.Getter;

@Getter
public class PMException extends RuntimeException {

    private final String sourceClass;
    private final boolean isSuccess;
   public PMException(String sourceClass, boolean isSuccess, String details) {
        super(details);
        this.sourceClass = sourceClass;
        this.isSuccess = isSuccess;
    }
}
