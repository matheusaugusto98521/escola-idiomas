package com.example.escolaIdiomas.services;

import com.example.escolaIdiomas.models.Course;
import com.example.escolaIdiomas.models.dto.CourseRequestDTO;
import com.example.escolaIdiomas.models.exceptions.InvallidCredentialsException;
import com.example.escolaIdiomas.repository.ICourseRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseServices {

    private ICourseRepository repository;

    @Transactional
    public Course registerCourse(CourseRequestDTO data) throws InvallidCredentialsException {
        Course course = new Course();
        if(data == null) throw new InvallidCredentialsException("Crdenciais não podem ser nulas ou vazias");
        BeanUtils.copyProperties(data, course);
        return this.repository.save(course);
    }
}
