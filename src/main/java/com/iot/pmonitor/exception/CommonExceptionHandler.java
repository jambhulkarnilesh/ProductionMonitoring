package com.iot.pmonitor.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PMException.class)
    public final ResponseEntity handlePMException(PMException exception, WebRequest request) {
        PMException pmException = new PMException(exception.getSourceClass(), exception.isSuccess(), exception.getMessage());
        return new ResponseEntity(pmException, HttpStatus.BAD_REQUEST);
    }
}
