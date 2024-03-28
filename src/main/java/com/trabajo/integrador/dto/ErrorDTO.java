package com.trabajo.integrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDTO {
    private final int status;
    private final String message;
}
