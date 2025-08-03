package com.eazybytes.accounts.resolver;

import com.eazybytes.accounts.GraphQlModalRequest.StudentResponse;
import com.eazybytes.accounts.GraphQlModalRequest.SubjectResponse;
import com.eazybytes.accounts.entity.Student;
import com.eazybytes.accounts.entity.Subject;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentResponseResolver {
    @SchemaMapping(typeName = "StudentResponse", field = "learningSubjects")
    public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse) {
        List<SubjectResponse> learningSubjects = new ArrayList<SubjectResponse>();
        Student student = studentResponse.getStudent();
        if (student.getLearningSubjects() != null) {
            for (Subject subject : student.getLearningSubjects()) {
                learningSubjects.add(new SubjectResponse(subject));
            }
        }
        return learningSubjects;
    }

    @SchemaMapping(typeName = "StudentResponse", field = "fullName")
    public String getFullName(StudentResponse studentResponse) {
        return studentResponse.getStudent().getFirstName() + " " + studentResponse.getStudent().getLastName();
    }
}