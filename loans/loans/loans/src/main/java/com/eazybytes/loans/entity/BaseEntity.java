package com.eazybytes.loans.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdAt;
    
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate updatedAt;
    
    @Column(insertable = true)
    @LastModifiedBy
    private String updatedBy;
}
