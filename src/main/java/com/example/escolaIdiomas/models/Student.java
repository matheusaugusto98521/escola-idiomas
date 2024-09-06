package com.example.escolaIdiomas.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Registration> registrations = new HashSet<>();

    public void setBirthDate(String birthDate){
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
        try{
            sfd.setLenient(false);
            this.birthDate = sfd.parse(birthDate);
        }catch (ParseException e){
            System.out.println("Formato de data inválido!!! [dd/MM/yyyy]");
        }
    }
}
