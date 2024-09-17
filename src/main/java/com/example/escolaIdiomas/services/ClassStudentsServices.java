package com.example.escolaIdiomas.services;

import com.example.escolaIdiomas.models.ClassStudents;
import com.example.escolaIdiomas.models.Registration;
import com.example.escolaIdiomas.models.Student;
import com.example.escolaIdiomas.models.dto.ClassStudentsRequestDTO;
import com.example.escolaIdiomas.models.exceptions.ClassStudentsNotFoundException;
import com.example.escolaIdiomas.models.exceptions.CourseNotFoundException;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.models.exceptions.TeacherNotFoundException;
import com.example.escolaIdiomas.repository.IClassStudentsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClassStudentsServices {

    private IClassStudentsRepository repository;
    private CourseServices courseServices;
    private TeacherServices teacherServices;

    @Transactional
    public ClassStudents registerClass(ClassStudentsRequestDTO data, UUID idCourse, UUID idTeacher) throws InvallidCredentialsException, CourseNotFoundException, TeacherNotFoundException {
        var classStudents = new ClassStudents();
        var courseFounded = this.courseServices.getById(idCourse);
        var teacherFounded = this.teacherServices.getById(idTeacher);
        if(data == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        BeanUtils.copyProperties(data, classStudents);
        classStudents.setCourse(courseFounded);
        classStudents.setTeacher(teacherFounded);
        this.courseServices.saveCourse(courseFounded);
        this.teacherServices.saveTeacher(teacherFounded);
        return saveClassBD(classStudents);
    }

    @Transactional
    public ClassStudents updateClass(UUID idClass, ClassStudentsRequestDTO updatedData) throws InvallidCredentialsException, ClassStudentsNotFoundException {
        var classStudentsFounded = getById(idClass);
        if(updatedData == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        BeanUtils.copyProperties(updatedData, classStudentsFounded);
        return saveClassBD(classStudentsFounded);
    }

    public void deleteClass(UUID idClass) throws ClassStudentsNotFoundException {
        var classStudentsFounded = getById(idClass);
        this.repository.delete(classStudentsFounded);
    }

    public List<ClassStudents> getAll(){
        return this.repository.findAll();
    }

    public ClassStudents getById(UUID idClass) throws ClassStudentsNotFoundException {
        return this.repository.findById(idClass).orElseThrow(()
                -> new ClassStudentsNotFoundException("Turma não encontrada para o ID: " + idClass));
    }

    public List<Student> getStudentsByClass(UUID idClass) throws ClassStudentsNotFoundException {
        var foundedClass = getById(idClass);
        return foundedClass.getRegistrations().stream()
                .map(Registration::getStudent)
                .collect(Collectors.toList());
    }


    private ClassStudents saveClassBD(ClassStudents classStudents) throws InvallidCredentialsException {
        if(classStudents == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        return this.repository.save(classStudents);
    }

    public void saveClass(ClassStudents classStudents) throws InvallidCredentialsException {
        if(classStudents == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        this.repository.save(classStudents);
    }
}
