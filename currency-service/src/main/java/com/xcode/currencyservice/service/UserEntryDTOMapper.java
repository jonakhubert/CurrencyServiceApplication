package com.xcode.currencyservice.service;

import com.xcode.currencyservice.model.UserEntry;
import com.xcode.currencyservice.model.dto.UserEntryDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserEntryDTOMapper implements Function<UserEntry, UserEntryDTO> {

    @Override
    public UserEntryDTO apply(UserEntry userEntry) {
        return new UserEntryDTO(
                userEntry.getCurrency(),
                userEntry.getName(),
                userEntry.getDate(),
                userEntry.getValue()
        );
    }
}
