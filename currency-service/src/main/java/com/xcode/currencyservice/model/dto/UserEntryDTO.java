package com.xcode.currencyservice.model.dto;

import java.time.LocalDateTime;

public record UserEntryDTO(
        String currency,
        String name,
        LocalDateTime date,
        double value
) {}
