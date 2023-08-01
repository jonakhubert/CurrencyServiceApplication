package com.xcode.currencyservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xcode.currencyservice.model.CurrencyRequest;
import com.xcode.currencyservice.model.CurrencyResponse;
import com.xcode.currencyservice.model.dto.UserEntryDTO;
import com.xcode.currencyservice.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetCurrentCurrencyValue_ValidCurrencyRequest_ReturnsCurrencyResponse() throws Exception {
        // arrange

        String currency = "EUR";
        String name = "Jan Kowalski";
        double value = 4.31;

        CurrencyRequest currencyRequest = new CurrencyRequest(currency, name);
        CurrencyResponse currencyResponse = new CurrencyResponse(value);

        when(currencyService.getCurrentCurrencyValue(currencyRequest)).thenReturn(currencyResponse);

        // act & assert

        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currencyRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(value));
    }

    @Test
    public void testGetCurrentCurrencyValue_InvalidCurrencyRequest_ReturnsBadRequest() throws Exception {
        // arrange

        CurrencyRequest currencyRequest = new CurrencyRequest(null, null);

        // act & assert

        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currencyRequest)))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400));
    }

    @Test
    public void testGetCurrentCurrencyValue_InvalidMediaType_ReturnsUnsupportedMediaType() throws Exception {
        // arrange

        String currency = "EUR";
        String name = "Jan Kowalski";
        double value = 4.31;

        CurrencyRequest currencyRequest = new CurrencyRequest(currency, name);
        CurrencyResponse currencyResponse = new CurrencyResponse(value);

        when(currencyService.getCurrentCurrencyValue(currencyRequest)).thenReturn(currencyResponse);

        // act & assert

        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                .contentType(MediaType.APPLICATION_XML)
                .content("{\"currency\": " + currency + ", \"name\": " + name + "}"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMediaTypeNotSupportedException))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(jsonPath("$.statusCode").value(415));
    }

    @Test
    public void testGetCurrentCurrencyValue_UnsupportedMethod_ReturnsMethodNotSupported() throws Exception {
        // arrange

        String currency = "EUR";
        String name = "Jan Kowalski";
        double value = 4.31;

        CurrencyRequest currencyRequest = new CurrencyRequest(currency, name);
        CurrencyResponse currencyResponse = new CurrencyResponse(value);

        when(currencyService.getCurrentCurrencyValue(currencyRequest)).thenReturn(currencyResponse);

        // act & assert

        mockMvc.perform(get("/currencies/get-current-currency-value-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currencyRequest)))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpRequestMethodNotSupportedException))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.statusCode").value(405));
    }

    @Test
    public void testGetCurrentCurrencyValue_InvalidMessageFormat_ReturnsBadRequest() throws Exception {
        // arrange

        String invalidJson = "{ invalid json payload";

        // act & assert

        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400));
    }

    @Test
    public void testGetUserEntries_ReturnsListOfUserEntryDTO() throws Exception {
        // arrange

        LocalDateTime date = LocalDateTime.now();

        List<UserEntryDTO> expected = Arrays.asList(
                new UserEntryDTO("USD", "John Doe", date, 3.54),
                new UserEntryDTO("EUR", "Jane Smith", date, 4.32)
        );

        when(currencyService.getAllEntries()).thenReturn(expected);

        // act & assert

        mockMvc.perform(get("/currencies/requests"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].currency").value(expected.get(0).currency()))
                .andExpect(jsonPath("$[0].name").value(expected.get(0).name()))
                .andExpect(jsonPath("$[0].value").value(expected.get(0).value()))
                .andExpect(jsonPath("$[1].currency").value(expected.get(1).currency()))
                .andExpect(jsonPath("$[1].name").value(expected.get(1).name()))
                .andExpect(jsonPath("$[1].value").value(expected.get(1).value()));
    }

    @Test
    public void testGetUserEntries_UnsupportedMethod_ReturnsMethodNotSupported() throws Exception {
        // act & assert

        mockMvc.perform(post("/currencies/requests"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpRequestMethodNotSupportedException))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.statusCode").value(405));
    }
}