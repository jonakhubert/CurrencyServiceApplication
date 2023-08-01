package com.xcode.currencyservice.model;

import jakarta.validation.constraints.NotNull;

public record CurrencyRequest(
        @NotNull String currency,
        @NotNull String name) {}
