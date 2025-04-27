package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.lang.annotation.Native;

@Data
public class AccountsDto {
    @NotEmpty(message = "Account Number can not be empty")
    @Pattern(regexp = "$[0-9]{10}", message = "Account Number should be 10 digits and should be valid")
    private Long accountNumber;
    @NotEmpty(message = "Account Type can not be empty")
    private String accountType;
    @NotEmpty(message = "Branch Address can not be empty")
    private String branchAddress;
    }
