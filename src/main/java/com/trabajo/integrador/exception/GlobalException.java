package com.trabajo.integrador.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    private static final Logger LOGGER= Logger.getLogger(GlobalException.class);

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<Error> CustomErrorHandler(CustomErrorException e){
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(e.getStatus())).body(new Error(
            e.getStatus(), e.getMessage()
        ));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> genericHandler(Exception e){
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()
        ));
    }
}
