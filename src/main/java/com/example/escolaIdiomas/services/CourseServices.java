package com.example.escolaIdiomas.services;

import com.example.escolaIdiomas.models.Course;
import com.example.escolaIdiomas.models.Teacher;
import com.example.escolaIdiomas.models.dto.CourseRequestDTO;
import com.example.escolaIdiomas.models.exceptions.CourseNotFoundException;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.repository.ICourseRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CourseServices {

    private ICourseRepository repository;

    @Transactional
    public Course registerCourse(CourseRequestDTO data) throws InvallidCredentialsException {
        Course course = new Course();
        if(data == null) throw new InvallidCredentialsException("Crdenciais não podem ser nulas ou vazias");
        BeanUtils.copyProperties(data, course);
        return saveCourseBD(course);
    }

    @Transactional
    public Course updateCourse(UUID idCourse, CourseRequestDTO data) throws CourseNotFoundException, InvallidCredentialsException {
        var foundedCourse = getById(idCourse);
        if(data == null) throw new InvallidCredentialsException("Crdenciais não podem ser nulas ou vazias");
        BeanUtils.copyProperties(data, foundedCourse);
        return saveCourseBD(foundedCourse);
    }

    public void deleteCourse(UUID idCourse) throws CourseNotFoundException {
        var foundedCourse = getById(idCourse);
        this.repository.delete(foundedCourse);
    }

    public List<Course> getAll(){
        return this.repository.findAll();
    }

    public Course getById(UUID idCourse) throws CourseNotFoundException {
        return this.repository.findById(idCourse).orElseThrow(()
                -> new CourseNotFoundException("Curso não encontrado para o ID: " + idCourse));
    }

    public List<Teacher> teachersByCourse(UUID idCourse) throws CourseNotFoundException {
        var foundedCourse = getById(idCourse);
        return foundedCourse.getTeachers();
    }


    public void saveCourse(Course course) throws InvallidCredentialsException {
        if(course == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        this.repository.save(course);
    }


    private Course saveCourseBD(Course course) throws InvallidCredentialsException {
        if(course == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        return this.repository.save(course);
    }
}
