package com.example.escolaIdiomas.controller;

import com.example.escolaIdiomas.models.Course;
import com.example.escolaIdiomas.models.Teacher;
import com.example.escolaIdiomas.models.dto.CourseRequestDTO;
import com.example.escolaIdiomas.models.exceptions.CourseNotFoundException;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.services.CourseServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private CourseServices services;

    @PostMapping("/register")
    public ResponseEntity<Course> register(@RequestBody @Valid CourseRequestDTO data) throws InvallidCredentialsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.services.registerCourse(data));
    }

    @PutMapping("/update")
    public ResponseEntity<Course> update(@RequestParam("idCourse")UUID idCourse,
                                         @RequestBody @Valid CourseRequestDTO updatedData) throws CourseNotFoundException, InvallidCredentialsException {
        return ResponseEntity.status(HttpStatus.OK).body(this.services.updateCourse(idCourse, updatedData));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("idCourse")UUID idCourse) throws CourseNotFoundException {
        this.services.deleteCourse(idCourse);
        return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso!");
    }

    @GetMapping("/")
    public ResponseEntity<List<Course>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.services.getAll());
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Course> getById(@RequestParam("idCourse")UUID idCourse) throws CourseNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(this.services.getById(idCourse));
    }

    @GetMapping("/teachers-by-course")
    public ResponseEntity<List<Teacher>> getTeachersByCourse(@RequestParam("idCourse")UUID idCourse) throws CourseNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(this.services.teachersByCourse(idCourse));
    }
}
