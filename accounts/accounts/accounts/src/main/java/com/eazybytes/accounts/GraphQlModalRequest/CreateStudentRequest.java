package com.eazybytes.accounts.GraphQlModalRequest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String street;
    private String city;
    private List<CreateSubjectRequest> sujectLearning;
}
