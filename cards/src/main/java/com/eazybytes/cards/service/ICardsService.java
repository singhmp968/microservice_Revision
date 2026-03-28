package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDto;
import org.springframework.beans.factory.ObjectProvider;

public interface ICardsService {
    void createCard(String mobileNumber);
    CardsDto fetchCards(String mobileNumber);
}
