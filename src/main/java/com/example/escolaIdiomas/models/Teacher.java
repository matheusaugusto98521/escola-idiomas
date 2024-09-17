package com.example.escolaIdiomas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_teachers")
@Data
@NoArgsConstructor
public class Teacher implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_teacher")
    private UUID id;

    @Column(name = "name_student", length = 100, nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(length = 200, nullable = false)
    private String degree;

    /*@Column(nullable = false)
    private double availableHours;*/

    /*@Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date availableDate;*/

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ClassStudents> classStudents = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

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
