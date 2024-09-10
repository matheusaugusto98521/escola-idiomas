package com.example.escolaIdiomas.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_registrations")
@Data
@NoArgsConstructor
public class Registration implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idRegistration")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_id", nullable = false)
    private ClassStudents classStudents;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @CreationTimestamp
    private LocalDateTime dateRegistration;


}
