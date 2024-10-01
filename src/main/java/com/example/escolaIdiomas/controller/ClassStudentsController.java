package com.example.escolaIdiomas.controller;

import com.example.escolaIdiomas.models.ClassStudents;
import com.example.escolaIdiomas.models.Student;
import com.example.escolaIdiomas.models.dto.ClassStudentsRequestDTO;
import com.example.escolaIdiomas.models.exceptions.ClassStudentsNotFoundException;
import com.example.escolaIdiomas.models.exceptions.CourseNotFoundException;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.models.exceptions.TeacherNotFoundException;
import com.example.escolaIdiomas.services.ClassServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/class")
@CrossOrigin("*")
public class ClassStudentsController {

    @Autowired
    private ClassServices services;

    @PostMapping("/register")
    public ResponseEntity<ClassStudents> register(@RequestBody @Valid ClassStudentsRequestDTO data,
                                                  @RequestParam("idCourse")UUID idCourse,
                                                  @RequestParam("idTeacher") UUID idTeacher) throws InvallidCredentialsException, TeacherNotFoundException, CourseNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.services.registerClass(data, idCourse, idTeacher));
    }

    @PutMapping("/update")
    public ResponseEntity<ClassStudents> update(@RequestParam("idClass") UUID idClass,
                                                @RequestBody @Valid ClassStudentsRequestDTO updatedData) throws ClassStudentsNotFoundException, InvallidCredentialsException {
        return ResponseEntity.status(HttpStatus.OK).body(this.services.updateClass(idClass, updatedData));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("idClass") UUID idClass) throws ClassStudentsNotFoundException {
        this.services.deleteClass(idClass);
        return ResponseEntity.status(HttpStatus.OK).body("Turma excluída com sucesso!");
    }

    @GetMapping("/")
    public ResponseEntity<List<ClassStudents>> getAllClasses(){
        return ResponseEntity.status(HttpStatus.OK).body(this.services.getAll());
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<ClassStudents> getClassById(@RequestParam("idClass") UUID idClass) throws ClassStudentsNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(this.services.getById(idClass));
    }

    @GetMapping("/get-students-by-class")
    public ResponseEntity<List<Student>> getStudentsByClass(@RequestParam("idClass") UUID idClass) throws ClassStudentsNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(this.services.getStudentsByClass(idClass));
    }

}
