package com.xcode.currencyservice.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public record UserEntryDTO(
        String currency,
        String name,
        LocalDateTime date,
        double value
) {
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if(!(o instanceof UserEntryDTO))
            return false;
        UserEntryDTO other = (UserEntryDTO) o;

        return Objects.equals(currency, other.currency) &&
                Objects.equals(name, other.name) &&
                Objects.equals(date, other.date) &&
                Double.compare(other.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, name, date, value);
    }
}
