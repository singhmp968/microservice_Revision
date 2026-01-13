package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constant.LoansConstants;
import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.RespouceNotFoundException;
import com.eazybytes.loans.mapper.LoanMapper;
import com.eazybytes.loans.repository.LoansRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {
    private final LoansRepository loansRepository;
    @Override
    public void createLoan(String mobileNumber) {
        // Implementation logic here
        Optional<Loans> loan = loansRepository.findByMobileNumber(mobileNumber);
        if(loan.isPresent()) {
            System.out.println("Loan already exists for mobile number: " + mobileNumber);
            // Add logic to handle existing loan scenario
            throw new LoanAlreadyExistsException("Loan already exists for mobile number: " + mobileNumber);
        } else {

            System.out.println("Creating new loan for mobile number: " + mobileNumber);
            // Add logic to create and save a new loan
            loansRepository.save(createLoanAccount(mobileNumber));
        }
    }

    @Override
    public LoanDto fetchLoanDetails(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new RespouceNotFoundException("Loan","mobileNumber",mobileNumber));

        return  LoanMapper.mapToLoanDTO(loans,new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loans loans =  loansRepository
                .findByMobileNumber(loanDto.getMobileNumber())
                .orElseThrow(()->new RespouceNotFoundException("Loan","mobileNumber",loanDto.getMobileNumber()));

        LoanMapper.mapToLoanEntity(loanDto,loans);
        loansRepository.save(loans);

        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository
                        .findByMobileNumber(mobileNumber)
                        .orElseThrow(()->new RespouceNotFoundException("Loan","mobileNumber",mobileNumber));
        loansRepository.delete(loans);
        return true;
    }

    private Loans createLoanAccount(String mobileNumber) {

        return Loans.builder()
                .mobileNumber(mobileNumber)
                .loanType(LoansConstants.HOME_LOAN)
                .totalLoan(LoansConstants.NEW_LOAN_LIMIT)
                .amountPaid(0)
                .outstandingAmount(LoansConstants.NEW_LOAN_LIMIT)
                .build();

    }
}
