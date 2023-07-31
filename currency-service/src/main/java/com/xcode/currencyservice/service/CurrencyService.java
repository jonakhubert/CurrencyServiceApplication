package com.xcode.currencyservice.service;

import com.xcode.currencyservice.model.CurrencyRequest;
import com.xcode.currencyservice.repository.CurrencyRepository;
import com.xcode.currencyservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
    }

    public Double getCurrentCurrencyValue(CurrencyRequest currencyRequest) {
        return currencyRepository.getCurrentCurrencyValue(currencyRequest).getValue();
    }
}