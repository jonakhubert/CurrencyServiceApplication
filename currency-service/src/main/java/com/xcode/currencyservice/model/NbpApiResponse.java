package com.xcode.currencyservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public record NbpApiResponse(
        @JsonProperty("rates") NbpCurrencyRate[] rates
) {}