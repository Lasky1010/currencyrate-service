package com.vadarodtest.currencyrateservice.service;

import com.vadarodtest.currencyrateservice.data.entity.CurrencyRate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface CurrencyRateService {

    ResponseEntity<String> saveRatesForDate(LocalDate date);

    Optional<CurrencyRate> getRateForDate(LocalDate date, String currencyCode);
}
