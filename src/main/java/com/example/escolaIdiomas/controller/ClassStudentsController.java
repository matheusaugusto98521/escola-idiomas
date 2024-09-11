package com.example.escolaIdiomas.controller;

import com.example.escolaIdiomas.models.ClassStudents;
import com.example.escolaIdiomas.models.dto.ClassStudentsRequestDTO;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.services.ClassStudentsServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class ClassStudentsController {

    @Autowired
    private ClassStudentsServices services;

    @PostMapping("/register")
    public ResponseEntity<ClassStudents> register(@RequestBody @Valid ClassStudentsRequestDTO data) throws InvallidCredentialsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.services.registerClass(data));
    }

}
