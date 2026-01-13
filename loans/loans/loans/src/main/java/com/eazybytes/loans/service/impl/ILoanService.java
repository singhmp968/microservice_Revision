package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.dto.LoanDto;

public interface ILoanService {
    void createLoan(String mobileNumber);
    LoanDto fetchLoanDetails(String mobileNumber);
    boolean updateLoan(LoanDto loanDto);
    boolean deleteLoan(String mobileNumber);
}
