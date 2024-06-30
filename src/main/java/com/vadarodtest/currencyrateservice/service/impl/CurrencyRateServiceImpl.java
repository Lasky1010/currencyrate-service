package com.vadarodtest.currencyrateservice.service.impl;

import com.vadarodtest.currencyrateservice.data.api.CurrencyRateApiResponse;
import com.vadarodtest.currencyrateservice.data.constant.ApiConstant;
import com.vadarodtest.currencyrateservice.data.entity.CurrencyRate;
import com.vadarodtest.currencyrateservice.data.mapper.CurrencyRateMapper;
import com.vadarodtest.currencyrateservice.repository.CurrencyRateRepository;
import com.vadarodtest.currencyrateservice.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;
    private final CurrencyRateMapper currencyRateMapper;

    @Override
    public ResponseEntity<String> saveRatesForDate(LocalDate date) {

        var url = String.format(ApiConstant.API_NBRB_URL_ON_DATE, date.toString());

        var restTemplate = new RestTemplate();
        var listOfApiRates = restTemplate.getForObject(url, CurrencyRateApiResponse[].class);

        if (Objects.requireNonNull(listOfApiRates).length != 0) {
            var listOfCurrencyRates = currencyRateMapper.mapTo(List.of(listOfApiRates));
            currencyRateRepository.saveAll(listOfCurrencyRates);
            return ResponseEntity.ok("Rates saved");
        }

        return ResponseEntity.ok("Rates not saved");
    }

    @Override
    public Optional<CurrencyRate> getRateForDate(LocalDate date, String currencyCode) {
        if (date == null) {
            date = LocalDate.now();
        }
        return currencyRateRepository.findByDateAndCurrencyCode(date, currencyCode);
    }

}
