package com.eazybytes.accounts.GraphQlModalRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubjectRequest {
    private String subjectName;
    private Double marksObtained;
}
