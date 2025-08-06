package com.eazybytes.accounts.GraphQlMutation;

import com.eazybytes.accounts.GraphQlModalRequest.CreateStudentRequest;
import com.eazybytes.accounts.GraphQlModalRequest.StudentResponse;
import com.eazybytes.accounts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Mutation {
    @Autowired
    StudentService studentService;
    @MutationMapping
    public StudentResponse createStudent(@Argument CreateStudentRequest createStudentRequest) {
        return new StudentResponse(studentService.createStudent(createStudentRequest));
    }
}
