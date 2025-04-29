package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {
    private CardsRepository cardsRepository;
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> card=  cardsRepository.findByMobileNumber(mobileNumber);
        if (!card.isPresent()) {
            throw new CardAlreadyExistsException("Card already exists for mobile number: " + mobileNumber);
        }
    }
}
