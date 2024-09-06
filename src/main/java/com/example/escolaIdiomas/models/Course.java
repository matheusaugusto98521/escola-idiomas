package com.example.escolaIdiomas.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_course")
@Data
@NoArgsConstructor
public class Course implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCourse")
    private UUID id;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(nullable = false)
    private String fullLoad;
}
