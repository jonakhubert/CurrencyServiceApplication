package com.xcode.currencyservice.service;

import com.xcode.currencyservice.model.UserEntry;
import com.xcode.currencyservice.model.CurrencyRequest;
import com.xcode.currencyservice.model.CurrencyResponse;
import com.xcode.currencyservice.repository.CurrencyRepository;
import com.xcode.currencyservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
    }

    public CurrencyResponse getCurrentCurrencyValue(CurrencyRequest currencyRequest) {
        UserEntry currencyHistory = currencyRepository.getCurrentCurrencyValue(currencyRequest);
        userRepository.save(currencyHistory);
        return new CurrencyResponse(currencyHistory.getValue());
    }

    public List<UserEntry> getAllEntries() {
        return userRepository.findAll();
    }
}