package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {
    IAccountsService iAccountsService;

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Account created successfully");
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAccount(@RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("Account updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Account deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
    }
}
