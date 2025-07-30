package com.eazybytes.accounts.service;

import com.eazybytes.accounts.entity.Student;
import com.eazybytes.accounts.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;
    public Student getStudentrById(Long id) {
        return studentRepo.findById(id).get();
    }
}
