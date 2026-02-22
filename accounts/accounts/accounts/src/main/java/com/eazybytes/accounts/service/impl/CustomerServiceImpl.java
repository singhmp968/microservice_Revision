package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CardsDto;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.LoanDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.client.CardsFeingClient;
import com.eazybytes.accounts.service.client.LoansFeingClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeingClient cardsFeingClient;
    private LoansFeingClient loansFeingClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String correlationId) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Customer Id", customer.getCustomerId().toString())
        );

       CustomerDetailsDto customerDetailsDto =CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
//        ResponseEntity<LoanDto> loansDtoReponseEntity = loansFeingClient.fetch(mobileNumber);
//        customerDetailsDto.setLoansDto(loansDtoReponseEntity.getBody());
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeingClient.fetchCardsDetails(correlationId,mobileNumber);
        if(null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        } else {
            System.out.println("No cards details found for mobile number: " + mobileNumber);
        }
        return customerDetailsDto;
    }
}
