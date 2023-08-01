package com.xcode.currencyservice.service;

import com.xcode.currencyservice.exception.model.CurrencyNotFoundException;
import com.xcode.currencyservice.model.UserEntry;
import com.xcode.currencyservice.model.CurrencyRequest;
import com.xcode.currencyservice.model.CurrencyResponse;
import com.xcode.currencyservice.model.dto.UserEntryDTO;
import com.xcode.currencyservice.repository.CurrencyRepository;
import com.xcode.currencyservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final UserEntryDTOMapper userEntryDTOMapper;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, UserRepository userRepository,
                           UserEntryDTOMapper userEntryDTOMapper) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
        this.userEntryDTOMapper = userEntryDTOMapper;
    }

    public CurrencyResponse getCurrentCurrencyValue(CurrencyRequest currencyRequest) {
        UserEntry userEntry = currencyRepository.getCurrentCurrencyValue(currencyRequest);

        if(userEntry == null) {
            throw new CurrencyNotFoundException(currencyRequest.currency() + " currency not found.");
        }

        userRepository.save(userEntry);
        return new CurrencyResponse(userEntry.getValue());
    }

    public List<UserEntryDTO> getAllEntries() {
        return userRepository.findAll()
                .stream()
                .map(userEntryDTOMapper)
                .collect(Collectors.toList());
    }
}