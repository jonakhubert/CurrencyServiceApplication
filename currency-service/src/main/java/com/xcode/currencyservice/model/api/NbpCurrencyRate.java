package com.xcode.currencyservice.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NbpCurrencyRate(
        @JsonProperty("code") String code,
        @JsonProperty("mid") double mid
) {}