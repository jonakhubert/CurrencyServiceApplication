package com.xcode.currencyservice.controller;

import com.xcode.currencyservice.model.CurrencyRequest;
import com.xcode.currencyservice.model.CurrencyResponse;
import com.xcode.currencyservice.model.UserEntry;
import com.xcode.currencyservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping(value = "/get-current-currency-value-command", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyResponse> getCurrentCurrencyValue(@RequestBody CurrencyRequest currencyRequest) {
        return ResponseEntity.ok(currencyService.getCurrentCurrencyValue(currencyRequest));
    }

    @GetMapping(value = "/requests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserEntry>> getUserEntries() {
        return ResponseEntity.ok(currencyService.getAllEntries());
    }
}
