package com.eazybytes.loans.mapper;

import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.entity.Loans;

public class LoanMapper {
    public static LoanDto mapToLoanDTO(Loans loans, LoanDto loansdto){
        loansdto.setLoanId(loans.getLoanId());
        loansdto.setMobileNumber(loans.getMobileNumber());
        loansdto.setLoanType(loans.getLoanType());
        loansdto.setTotalLoan(loans.getTotalLoan());
        loansdto.setAmountPaid(loans.getAmountPaid());
        loansdto.setOutstandingAmount(loans.getOutstandingAmount());



//        loansDto.setLoanType(loans.getLoanType());
//        loansDto.setTotalLoan(loans.getTotalLoan());
//        loansDto.setAmountPaid(loans.getAmountPaid());
//        loansDto.setOutstandingAmount(loans.getOutstandingAmount());

        return loansdto;
    }

    public static Loans mapToLoanEntity(LoanDto loansdto, Loans loans){

        loans.setMobileNumber(loansdto.getMobileNumber());
        loans.setLoanType(loansdto.getLoanType());
        loans.setTotalLoan(loansdto.getTotalLoan());
        loans.setAmountPaid(loansdto.getAmountPaid());
        loans.setOutstandingAmount(loansdto.getOutstandingAmount());

        return loans;
    }
}
