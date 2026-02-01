package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.AccountContactInfoDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    @Value("${build.version}")
    private String buildVersion;
    @Autowired
    private Environment environment;
    @Autowired
    private AccountContactInfoDto accountContactInfoDto;
    private final IAccountsService iAccountsService;

    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }
    @Operation(summary = "Create a new account",
            description = "This API is used to create a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Account created successfully"),
    })
    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Account created successfully");
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @Pattern(regexp = "$[0-9]{10}", message = "Mobile number should be 10 digits and should be valid")
            @RequestParam String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
    @Operation(summary = "update a existing account",
            description = "This API is used to create a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Account updateds successfully"),
            @ApiResponse(responseCode = "500",description = "Account not updateds successfully")
    })
    @PutMapping("/update")
    public ResponseEntity<String> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("Account updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

    }

    @Operation(summary = "delete a existing account",
            description = "This API is used to delete a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Account deleted successfully"),
            @ApiResponse(responseCode = "500",description = "Account not deleted successfully")
    })

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam
                                                    @Pattern(regexp = "$[0-9]{10}", message = "Mobile number should be 10 digits and should be valid")
                                                    String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Account deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
    }

    @Operation(summary = "Get Build Information",
            description = "Get build information that deployed into the accounts microservices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500",description = "Account not deleted successfully")
    })

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @Operation(summary = "Get Java Information",
            description = "Get java version details /that si installed into accounts microservices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500",description = "Account not deleted successfully")
    })

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("MAVEN_HOME"));
    }


    @Operation(summary = "Get Contact Information",
            description = "Get Contact version details /that si installed into accounts microservices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500",description = "Account not deleted successfully")
    })

    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountContactInfoDto);
    }

}
