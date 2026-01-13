package com.eazybytes.loans.controller;

import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.dto.ResponseDto;
import com.eazybytes.loans.service.impl.ILoanService;
import com.eazybytes.loans.service.impl.LoanServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class LoanController {
    @Autowired
    private final ILoanService iLoanService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam String mobileNumber) {
        iLoanService.createLoan(mobileNumber);
        ResponseDto responseDto = new ResponseDto("200", "Loan created successfully");
        return ResponseEntity.ok(responseDto);
      }

      @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetch(@RequestParam String mobileNumber){
          LoanDto loanDto = iLoanService.fetchLoanDetails(mobileNumber);
          return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(loanDto);
      }

     @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody LoanDto loanDto){

       boolean isUpdate =  iLoanService.updateLoan(loanDto);
       if(!isUpdate){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDto("500","Loan not updated successfully"));
       }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Loan updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam String mobileNumber){
       boolean isdelete=  iLoanService.deleteLoan(mobileNumber);
       if(!isdelete){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDto("500","Loan not deleted successfully"));
       }
       // Deletion logic would go here
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Loan deleted successfully"));
    }

}
