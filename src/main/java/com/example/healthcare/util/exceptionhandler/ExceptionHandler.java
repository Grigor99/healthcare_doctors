package com.example.healthcare.util.exceptionhandler;

import com.example.healthcare.util.exceptionhandler.exceptions.DuplicateException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateException.class)
    @ResponseBody
    public ErrorRestResponse handleDuplicateException(DuplicateException ex) {
        LOGGER.error(ex.getMessage() + "::::" + ex.getStackTrace());
        return new ErrorRestResponse(409, ex.getMessage());
    }

}
