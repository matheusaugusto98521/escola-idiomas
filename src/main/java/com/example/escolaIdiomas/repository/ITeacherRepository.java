package com.example.escolaIdiomas.repository;

import com.example.escolaIdiomas.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, UUID> {
}
