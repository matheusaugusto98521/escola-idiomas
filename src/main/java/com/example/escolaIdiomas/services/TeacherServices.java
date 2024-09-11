package com.example.escolaIdiomas.services;

import com.example.escolaIdiomas.repository.ITeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServices {

    private ITeacherRepository repository;
}
