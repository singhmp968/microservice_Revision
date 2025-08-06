package com.eazybytes.accounts.service;

import com.eazybytes.accounts.GraphQlModalRequest.CreateStudentRequest;
import com.eazybytes.accounts.GraphQlModalRequest.CreateSubjectRequest;
import com.eazybytes.accounts.entity.Address;
import com.eazybytes.accounts.entity.Student;
import com.eazybytes.accounts.entity.Subject;
import com.eazybytes.accounts.repository.AddressRepository;
import com.eazybytes.accounts.repository.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class StudentService {
    @Autowired
    private final StudentRepo studentRepo;
    private final AddressRepository addressRepo;

    public Student getStudentrById(Long id) {
        return studentRepo.findById(id).get();
    }
    // MutationQuery to create student
    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);
        Address address = new Address();

        address.setCity(createStudentRequest.getCity());
        address.setStreet(createStudentRequest.getStreet());
        address = addressRepo.save(address);

        student.setAddress(address);
        student = studentRepo.save(student);

        List<Subject> learningSubjects = new ArrayList<>();
        if (createStudentRequest.getSujectLearning() != null) {
            for (CreateSubjectRequest createSubjectRequest : createStudentRequest.getSujectLearning()) {
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarksObtained(createSubjectRequest.getMarksObtained());
                subject.setStudent(student);
                learningSubjects.add(subject);
            }
        }
        student.setLearningSubjects(learningSubjects);
        return studentRepo.save(student);

    }


}
