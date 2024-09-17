package com.example.escolaIdiomas.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends Exception{
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
