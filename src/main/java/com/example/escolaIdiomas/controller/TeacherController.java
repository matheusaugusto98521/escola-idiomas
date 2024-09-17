package com.example.escolaIdiomas.controller;

import com.example.escolaIdiomas.models.Teacher;
import com.example.escolaIdiomas.models.dto.TeacherRequestDTO;
import com.example.escolaIdiomas.models.exceptions.CourseNotFoundException;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.models.exceptions.TeacherNotFoundException;
import com.example.escolaIdiomas.services.TeacherServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teacher")
@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private TeacherServices services;

    @PostMapping("/register")
    public ResponseEntity<Teacher> register(@RequestBody @Valid TeacherRequestDTO data,
                                            @RequestParam("idCourse") UUID idCourse) throws CourseNotFoundException, InvallidCredentialsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.services.register(data, idCourse));
    }

    @PutMapping("/update")
    public ResponseEntity<Teacher> updateTeacher(@RequestParam("idTeacher") UUID idTeacher,
                                                 @RequestBody @Valid TeacherRequestDTO updatedData) throws TeacherNotFoundException, InvallidCredentialsException {
        return ResponseEntity.status(HttpStatus.OK).body(this.services.updateTeacher(idTeacher, updatedData));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTeacher(@RequestParam("idTeacher") UUID idTeacher) throws TeacherNotFoundException {
        this.services.deleteTeacher(idTeacher);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso");
    }

    @GetMapping("/")
    public ResponseEntity<List<Teacher>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.services.getAll());
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Teacher> getById(@RequestParam("idTeacher") UUID idTeacher) throws TeacherNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(this.services.getById(idTeacher));
    }
}
