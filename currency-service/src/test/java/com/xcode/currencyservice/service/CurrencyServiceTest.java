package com.xcode.currencyservice.service;

import com.xcode.currencyservice.exception.model.CurrencyNotFoundException;
import com.xcode.currencyservice.model.CurrencyRequest;
import com.xcode.currencyservice.model.CurrencyResponse;
import com.xcode.currencyservice.model.UserEntry;
import com.xcode.currencyservice.model.dto.UserEntryDTO;
import com.xcode.currencyservice.repository.ICurrencyRepository;
import com.xcode.currencyservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private ICurrencyRepository currencyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntryDTOMapper userEntryDTOMapper;

    @InjectMocks
    private CurrencyService underTest;

    @Test
    public void testGetCurrentCurrencyValue_ValidCurrencyRequest_ReturnsCurrencyResponse() {
        // arrange

        String currency = "EUR";
        String name = "Jan Kowalski";
        LocalDateTime date = LocalDateTime.now();
        double value = 4.41;

        CurrencyRequest currencyRequest = new CurrencyRequest(currency, name);

        UserEntry userEntry = new UserEntry(currency, name, date, value);
        when(currencyRepository.getCurrentCurrencyValue(currencyRequest)).thenReturn(userEntry);

        // act

        CurrencyResponse result = underTest.getCurrentCurrencyValue(currencyRequest);

        // assert

        assertEquals(value, result.value());
        verify(userRepository, times(1)).save(userEntry);
    }

    @Test
    public void testGetCurrentCurrencyValue_InvalidCurrencyRequest_ThrowsCurrencyNotFoundException() {
        // arrange

        String currency = "INVALID";
        String name = "Jan Kowalski";

        CurrencyRequest currencyRequest = new CurrencyRequest(currency, name);

        when(currencyRepository.getCurrentCurrencyValue(currencyRequest)).thenReturn(null);

        // act & assert

        assertThrows(CurrencyNotFoundException.class, () -> underTest.getCurrentCurrencyValue(currencyRequest));
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testGetAllEntries_ReturnsListOfUserEntryDTO() {
        // arrange

        LocalDateTime date = LocalDateTime.now();

        List<UserEntry> userEntries = Arrays.asList(
                new UserEntry("USD", "John Doe", date, 3.54),
                new UserEntry("EUR", "Jane Smith", date, 4.32)
        );

        List<UserEntryDTO> expected = Arrays.asList(
                new UserEntryDTO("USD", "John Doe", date, 3.54),
                new UserEntryDTO("EUR", "Jane Smith", date, 4.32)
        );

        when(userRepository.findAll()).thenReturn(userEntries);
        when(userEntryDTOMapper.apply(any(UserEntry.class)))
                .thenReturn(expected.get(0))
                .thenReturn(expected.get(1));

        // act

        List<UserEntryDTO> result = underTest.getAllEntries();

        // assert

        assertEquals(expected, result);
    }
}