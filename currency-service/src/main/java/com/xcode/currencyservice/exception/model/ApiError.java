package com.xcode.currencyservice.exception.model;

import java.time.LocalDateTime;

public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDateTime time
) {}