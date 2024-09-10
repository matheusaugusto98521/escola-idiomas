package com.example.escolaIdiomas.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_class_students")
@Data
@NoArgsConstructor
public class ClassStudents implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_class")
    private UUID id;

    @Column(length = 100, nullable = false)
    private String description;

    @OneToMany(mappedBy = "classStudents", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Registration> registrations = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;
}
