package com.example.escolaIdiomas.services;

import com.example.escolaIdiomas.models.ClassStudents;
import com.example.escolaIdiomas.models.dto.ClassStudentsRequestDTO;
import com.example.escolaIdiomas.models.exceptions.ClassStudentsNotFoundException;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.repository.IClassStudentsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClassStudentsServices {

    private IClassStudentsRepository repository;

    public ClassStudents registerClass(ClassStudentsRequestDTO data) throws InvallidCredentialsException {
        var classStudents = new ClassStudents();
        if(data == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        BeanUtils.copyProperties(data, classStudents);
        return saveClassBD(classStudents);
    }

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

    @Transactional
    private ClassStudents saveClassBD(ClassStudents classStudents) throws InvallidCredentialsException {
        if(classStudents == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        return this.repository.save(classStudents);
    }

    @Transactional
    public void saveClass(ClassStudents classStudents) throws InvallidCredentialsException {
        if(classStudents == null) throw new InvallidCredentialsException("Credenciais não podem ser nulas ou vazias");
        this.repository.save(classStudents);
    }
}
