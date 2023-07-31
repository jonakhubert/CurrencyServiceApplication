package com.xcode.currencyservice.controller;

import com.xcode.currencyservice.model.CurrencyRequest;
import com.xcode.currencyservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity<Double> getCurrentCurrencyValue(@RequestBody CurrencyRequest currencyRequest) {
        double result = currencyService.getCurrentCurrencyValue(currencyRequest);

        return ResponseEntity.ok(result);
    }
}
