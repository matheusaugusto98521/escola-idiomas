package com.example.escolaIdiomas.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClassStudentsNotFoundException extends Exception{
    public ClassStudentsNotFoundException(String message) {
        super(message);
    }
}
