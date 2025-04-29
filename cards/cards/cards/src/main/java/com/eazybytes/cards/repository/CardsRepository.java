package com.eazybytes.cards.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository {
    Optional<Cards> findByMobileNumber(String mobileNumber);
}
