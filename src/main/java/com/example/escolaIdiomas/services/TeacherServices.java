package com.example.escolaIdiomas.services;

import com.example.escolaIdiomas.models.Teacher;
import com.example.escolaIdiomas.models.dto.TeacherRequestDTO;
import com.example.escolaIdiomas.models.exceptions.CourseNotFoundException;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.models.exceptions.TeacherNotFoundException;
import com.example.escolaIdiomas.repository.ITeacherRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeacherServices {

    private ITeacherRepository repository;
    private CourseServices courseServices;

    @Transactional
    public Teacher register(TeacherRequestDTO data, UUID idCourse) throws CourseNotFoundException, InvallidCredentialsException {
        var teacher = new Teacher();
        var courseFounded = this.courseServices.getById(idCourse);
        if(data == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        BeanUtils.copyProperties(data, teacher);
        teacher.setCourse(courseFounded);
        this.courseServices.saveCourse(courseFounded);
        return this.repository.save(teacher);
    }

    @Transactional
    public Teacher updateTeacher(UUID idTeacher, TeacherRequestDTO updatedData) throws TeacherNotFoundException, InvallidCredentialsException {
        var foundedTeacher = getById(idTeacher);
        if(updatedData == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        BeanUtils.copyProperties(updatedData, foundedTeacher);
        return this.repository.save(foundedTeacher);
    }

    public void deleteTeacher(UUID idTeacher) throws TeacherNotFoundException {
        var foundedTeacher = getById(idTeacher);
        this.repository.delete(foundedTeacher);
    }

    public List<Teacher> getAll(){
        return this.repository.findAll();
    }

    public Teacher getById(UUID idTeacher) throws TeacherNotFoundException {
        return this.repository.findById(idTeacher).orElseThrow(()
                -> new TeacherNotFoundException("Professor não encontrado para o ID: " + idTeacher));
    }

    public void saveTeacher(Teacher teacher) throws InvallidCredentialsException {
        if(teacher == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        this.repository.save(teacher);
    }
}
