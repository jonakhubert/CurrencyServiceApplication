package com.xcode.currencyservice.repository;

import com.xcode.currencyservice.model.UserEntry;
import com.xcode.currencyservice.model.CurrencyRequest;

public interface ICurrencyRepository {
    UserEntry getCurrentCurrencyValue(CurrencyRequest currencyRequest);
}
