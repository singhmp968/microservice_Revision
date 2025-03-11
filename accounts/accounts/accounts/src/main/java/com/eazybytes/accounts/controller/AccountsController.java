package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.service.impl.AccountsServiceImpll;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {
AccountsServiceImpll accountsService;
@PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody CustomerDto customerDto) {
    accountsService.createAccount(customerDto);
    return ResponseEntity
                .status(HttpStatus.OK)
                .body("Account created successfully");
    }
}
