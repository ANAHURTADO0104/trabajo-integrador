package com.trabajo.integrador.exception;

import lombok.Getter;

@Getter
public class CustomErrorException extends Exception{
    private final int status;
    public CustomErrorException (String message, int status){
        super(message);
        this.status = status;
    }
}
