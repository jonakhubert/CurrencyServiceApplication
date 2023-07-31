package com.xcode.currencyservice.repository;

import com.xcode.currencyservice.model.CurrencyHistory;
import com.xcode.currencyservice.model.CurrencyRequest;

public interface ICurrencyRepository {
    CurrencyHistory getCurrentCurrencyValue(CurrencyRequest currencyRequest);
}
