package com.xcode.currencyservice.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import com.xcode.currencyservice.model.CurrencyRequest;
import com.xcode.currencyservice.model.UserEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyRepositoryTest {

    @Mock
    private ICurrencyRepository currencyRepository;

    @Test
    public void testGetCurrentCurrencyValue_WhenCurrencyExist() {
        // arrange

        String currency = "EUR";
        String name = "Jan Kowalski";
        LocalDateTime date = LocalDateTime.now();
        double value = 4.41;

        CurrencyRequest currencyRequest = new CurrencyRequest(currency, name);
        UserEntry expected = new UserEntry(currency, name, date, value);

        when(currencyRepository.getCurrentCurrencyValue(currencyRequest)).thenReturn(expected);

        // act

        UserEntry result = currencyRepository.getCurrentCurrencyValue(currencyRequest);

        // assert

        assertEquals(expected, result);
    }

    @Test
    public void testGetCurrentCurrencyValue_WhenCurrencyDoesNotExist() {
        // arrange

        String currency = "INVALID";
        String name = "Jan Kowalski";

        CurrencyRequest currencyRequest = new CurrencyRequest(currency, name);

        when(currencyRepository.getCurrentCurrencyValue(currencyRequest)).thenReturn(null);

        // act

        UserEntry result = currencyRepository.getCurrentCurrencyValue(currencyRequest);

        // assert

        assertNull(result);
    }
}