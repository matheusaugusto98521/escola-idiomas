package com.example.escolaIdiomas.controller;

import com.example.escolaIdiomas.models.Student;
import com.example.escolaIdiomas.models.dto.StudentRequestDTO;
import com.example.escolaIdiomas.models.exceptions.ClassStudentsNotFoundException;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.services.StudentServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServices services;

    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody @Valid StudentRequestDTO data
                                            ,@RequestParam("idClass") UUID idClass) throws ClassStudentsNotFoundException, InvallidCredentialsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.services.registerStudent(data, idClass));
    }
}
