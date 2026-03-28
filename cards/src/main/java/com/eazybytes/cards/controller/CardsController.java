package com.eazybytes.cards.controller;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api",
                produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {

    private static final Logger logger = LoggerFactory.getLogger(CardsController.class);
    ICardsService iCardsService;

    @PostMapping("/create")
    public  ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber) {
        iCardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));    }


@GetMapping("/fetch")
public ResponseEntity<CardsDto> fetchCardsDetails(@RequestHeader("eazybank-correlation-id") String correlationId ,
                                                  @RequestParam String mobileNumber) {
    logger.debug("eazyBank-correlation-id found: {}", correlationId);
    CardsDto cardsDto = iCardsService.fetchCards(mobileNumber);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardsDto);
}
}