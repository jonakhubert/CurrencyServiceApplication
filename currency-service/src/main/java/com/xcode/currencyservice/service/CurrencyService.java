package com.xcode.currencyservice.service;

import com.xcode.currencyservice.model.CurrencyRequest;
import com.xcode.currencyservice.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Double getCurrentCurrencyValue(CurrencyRequest currencyRequest) {
        return currencyRepository.getCurrentCurrencyValue(currencyRequest).getValue();
    }
}