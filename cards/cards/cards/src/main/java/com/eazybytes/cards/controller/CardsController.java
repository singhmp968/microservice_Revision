package com.eazybytes.cards.controller;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",
                produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {
    ICardsService iCardsService;

    @PostMapping("/create")
    public  ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber) {
        iCardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));    }
}