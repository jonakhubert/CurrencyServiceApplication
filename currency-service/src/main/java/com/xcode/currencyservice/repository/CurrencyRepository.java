package com.xcode.currencyservice.repository;

import com.xcode.currencyservice.model.UserEntry;
import com.xcode.currencyservice.model.CurrencyRequest;
import com.xcode.currencyservice.model.api.NbpApiResponse;
import com.xcode.currencyservice.model.api.NbpCurrencyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Repository
public class CurrencyRepository implements ICurrencyRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserEntry getCurrentCurrencyValue(CurrencyRequest currencyRequest) {
        String url = "http://api.nbp.pl/api/exchangerates/tables/A?format=json";
        NbpApiResponse[] response = restTemplate.getForObject(url, NbpApiResponse[].class);

        String currency = currencyRequest.currency().toUpperCase();

        if(response != null) {
            for(NbpCurrencyRate rate : response[0].rates()) {
                if(rate.code().equals(currency)) {
                    return new UserEntry(
                            currency,
                            currencyRequest.name(),
                            LocalDateTime.now(),
                            rate.mid()
                    );
                }
            }
        }

        return null;
    }
}
