package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "CustomerDetails",
        description = "Customer Details Data Transfer Object")
public class CustomerDetailsDto {

    @NotEmpty(message = "Name can not be empty")
    @Size(min = 3,max=30, message = "Name should have at least 3 characters")
    private String name;
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Accounts details of the customer")
    private AccountsDto accountsDto;

    @Schema(description = "Loans details of the customer")
    private LoanDto loansDto;

    @Schema(description = "Customer details of the customer")
    private CardsDto cardsDto;

}
