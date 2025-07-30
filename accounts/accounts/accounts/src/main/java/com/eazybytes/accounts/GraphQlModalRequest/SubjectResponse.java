package com.eazybytes.accounts.GraphQlModalRequest;

import com.eazybytes.accounts.entity.Subject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class SubjectResponse {
    private Long id;
    private String subjectName;
    private Double marksObtained;

    public SubjectResponse(Subject subject) {
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.marksObtained = subject.getMarksObtained();
    }
}
