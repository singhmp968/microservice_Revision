package com.eazybytes.accounts.GQLquery;

import com.eazybytes.accounts.GraphQlModalRequest.SampleRequest;
import com.eazybytes.accounts.GraphQlModalRequest.StudentResponse;
import com.eazybytes.accounts.entity.Student;
import com.eazybytes.accounts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Query {
    @Autowired
    private StudentService studentService;
    @QueryMapping
    public String hello() {
        return "Hello GQL";
    }
    @QueryMapping
    public String paramsRevise(@Argument String fName, @Argument String lName) {

        return fName + " " + lName;
    }
    @QueryMapping
    public String sampleRequestJson(@Argument SampleRequest sampleRequest) {
        return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
    }
    @QueryMapping
    public StudentResponse studentData(@Argument String id) {
        Long id1 = Long.parseLong(id);
        Student ss= studentService.getStudentrById(id1);
        return new StudentResponse(ss);
    }
}
