package com.iot.pmonitor.exception;

import lombok.Data;
import lombok.Getter;

@Data
public class PMException extends RuntimeException {

    private static final long serialVersionUID  = 1L;
    private final String sourceClass;
    private final boolean isSuccess;
   public PMException(String sourceClass, boolean isSuccess, String details) {
        super(details);
        this.sourceClass = sourceClass;
        this.isSuccess = isSuccess;
    }
}
