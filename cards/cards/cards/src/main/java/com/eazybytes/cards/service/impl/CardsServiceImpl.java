package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {
    private CardsRepository cardsRepository;
    @Override
    public void createCard(String mobileNumber) {

    }
}
