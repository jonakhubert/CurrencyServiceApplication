package com.xcode.currencyservice.repository;

import com.xcode.currencyservice.model.CurrencyHistory;
import com.xcode.currencyservice.model.CurrencyRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface ICurrencyRepository {
    CurrencyHistory getCurrentCurrencyValue(CurrencyRequest currencyRequest);
}
