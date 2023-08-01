package com.xcode.currencyservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.xcode.currencyservice.model.UserEntry;
import com.xcode.currencyservice.model.dto.UserEntryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class UserEntryDTOMapperTest {

    private UserEntryDTOMapper underTest;

    @BeforeEach
    public void setUp() {
        underTest = new UserEntryDTOMapper();
    }

    @Test
    public void testApply_WithValidUserEntry_ShouldMapToUserEntryDTO() {
        // arrange

        String currency = "EUR";
        String name = "Jan Kowalski";
        LocalDateTime date = LocalDateTime.of(2023, 8, 1, 12, 0); // Replace with your desired date
        double value = 4.41;

        UserEntry userEntry = new UserEntry(currency, name, date, value);

        // act

        UserEntryDTO result = underTest.apply(userEntry);

        // assert

        assertEquals(currency, result.currency());
        assertEquals(name, result.name());
        assertEquals(date, result.date());
        assertEquals(value, result.value(), 0.001);
    }
}