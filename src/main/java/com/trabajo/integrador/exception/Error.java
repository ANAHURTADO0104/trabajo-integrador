package com.trabajo.integrador.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Error {
    private final int status;
    private final String message;
}
