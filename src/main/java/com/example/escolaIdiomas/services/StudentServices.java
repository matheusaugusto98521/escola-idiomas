package com.example.escolaIdiomas.services;

import com.example.escolaIdiomas.models.Student;
import com.example.escolaIdiomas.models.dto.StudentRequestDTO;
import com.example.escolaIdiomas.models.exceptions.ClassStudentsNotFoundException;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.models.exceptions.StudentNotFoundException;
import com.example.escolaIdiomas.repository.IRegistrationRepository;
import com.example.escolaIdiomas.repository.IStudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentServices {

    private IStudentRepository repository;
    private ClassServices classServices;
    private IRegistrationRepository registrationRepository;

    @Transactional
    public Student registerStudent(StudentRequestDTO data, UUID idClass) throws InvallidCredentialsException, ClassStudentsNotFoundException {
        var classFounded = classServices.getById(idClass);
        var student = new Student();
        if(data == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        BeanUtils.copyProperties(data, student);
        student.setAge(student.calculateAge());
        student.addClass(classFounded);
        saveStudent(student);
        classServices.saveClass(classFounded);
        return student;
    }

    @Transactional
    public Student updateStudent(UUID idStudent, StudentRequestDTO data) throws StudentNotFoundException, InvallidCredentialsException {
        var foundedStudent = getStudentById(idStudent);
        if(data == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        BeanUtils.copyProperties(data, foundedStudent);
        return saveStudentBD(foundedStudent);
    }

    public void deleteStudent(UUID idStudent) throws StudentNotFoundException {
        var foundedStudent = getStudentById(idStudent);
        foundedStudent.getRegistrations().forEach(registration -> {
            registration.setStudent(null);
            registration.setClassStudents(null);
            registrationRepository.delete(registration);
        });
        this.repository.delete(foundedStudent);
    }

    public List<Student> getAll(){
        return this.repository.findAll();
    }

    public Student getStudentById(UUID idStudent) throws StudentNotFoundException {
        return this.repository.findById(idStudent).orElseThrow(()
                -> new StudentNotFoundException("Aluno não encontrado para o ID: " + idStudent));
    }

    private Student saveStudentBD(Student student) throws InvallidCredentialsException {
        if(student == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        return this.repository.save(student);

    }


    public void saveStudent(Student student) throws InvallidCredentialsException {
        if(student == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        this.repository.save(student);
    }
}
