package com.eazybytes.accounts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
   private Long id;

    @Column(name = "subjectName")
    private String subjectName;

    @Column(name = "marksObtained")
    private Double marksObtained;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
