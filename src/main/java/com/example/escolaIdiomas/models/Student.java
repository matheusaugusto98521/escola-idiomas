package com.example.escolaIdiomas.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_student")
@Data
@NoArgsConstructor
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idStudent")
    private UUID id;

    @Column(name = "nameStudent", nullable = false, length = 100)
    private String name;


    private int age;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate;

    @Column(length = 200, nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String cpf;
}
