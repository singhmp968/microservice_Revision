package com.eazybytes.accounts.GraphQlModalRequest;

import com.eazybytes.accounts.entity.Student;
import com.eazybytes.accounts.entity.Subject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String city;
    private String fullName;
    private Student student;
private List<SubjectResponse> learningSubjects;
    public StudentResponse(Student student) {
        this.student = student;
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.street = student.getAddress().getStreet();
        this.city = student.getAddress().getCity();
//        if (student.getLearningSubjects() != null){
//            learningSubjects = new ArrayList<SubjectResponse>();
//            for(Subject subject : student.getLearningSubjects()){
//                learningSubjects.add(new SubjectResponse(subject));
//            }


//        }
    }

}
