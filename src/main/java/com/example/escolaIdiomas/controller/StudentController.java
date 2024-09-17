package com.example.escolaIdiomas.controller;

import com.example.escolaIdiomas.models.Course;
import com.example.escolaIdiomas.models.Student;
import com.example.escolaIdiomas.models.dto.StudentRequestDTO;
import com.example.escolaIdiomas.models.exceptions.ClassStudentsNotFoundException;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.models.exceptions.StudentNotFoundException;
import com.example.escolaIdiomas.services.StudentServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentServices services;

    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody @Valid StudentRequestDTO data
                                            ,@RequestParam("idClass") UUID idClass) throws ClassStudentsNotFoundException, InvallidCredentialsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.services.registerStudent(data, idClass));
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam("idStudent") UUID idStudent,
                                                 @RequestBody @Valid StudentRequestDTO updatedData) throws StudentNotFoundException, InvallidCredentialsException {
        return ResponseEntity.status(HttpStatus.OK).body(this.services.updateStudent(idStudent, updatedData));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteStudent(@RequestParam("idStudent") UUID idStudent) throws StudentNotFoundException {
        this.services.deleteStudent(idStudent);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(this.services.getAll());
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Student> getStudentById(@RequestParam("idStudent") UUID idStudent) throws StudentNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(this.services.getStudentById(idStudent));
    }
}
