package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class LoanDto {


    private Long loanId;
    @NotEmpty(message = "Mobile number cannot be empty")
    private String mobileNumber;
    @NotEmpty(message = "Loan number cannot be empty")
    private int loanNumber;
    @NotEmpty(message = "Loan type cannot be empty")
    private String loanType;
    @NotEmpty(message = "Total loan cannot be empty")
    private int totalLoan;
    @NotEmpty(message = "Amount paid cannot be empty")
    private int amountPaid;
    @NotEmpty(message = "Outstanding amount cannot be empty")
    private int outstandingAmount;

}
