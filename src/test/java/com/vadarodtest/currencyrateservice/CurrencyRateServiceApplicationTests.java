package com.vadarodtest.currencyrateservice;

import com.vadarodtest.currencyrateservice.data.entity.CurrencyRate;
import com.vadarodtest.currencyrateservice.repository.CurrencyRateRepository;
import com.vadarodtest.currencyrateservice.service.impl.CurrencyRateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyRateServiceApplicationTests {

    public static final String USD_CODE = "USD";

    @Mock
    private CurrencyRateRepository currencyRateRepository;

    @InjectMocks
    private CurrencyRateServiceImpl currencyRateService;

    @Test
    public void testGetRateForDate() {
        LocalDate date = LocalDate.of(2024, 6, 29);
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setDate(date);
        currencyRate.setCurrencyCode(USD_CODE);
        currencyRate.setRate(2.5);
        when(currencyRateRepository.findByDateAndCurrencyCode(date, USD_CODE)).thenReturn(Optional.of(currencyRate));

        Optional<CurrencyRate> result = currencyRateService.getRateForDate(date, USD_CODE);
        assertTrue(result.isPresent());
        assertEquals(currencyRate, result.get());
    }


    @Test
    public void testGetRateForDate_NotFound() {
        LocalDate date = LocalDate.of(2023, 6, 29);

        when(currencyRateRepository.findByDateAndCurrencyCode(date, USD_CODE)).thenReturn(Optional.empty());

        Optional<CurrencyRate> result = currencyRateService.getRateForDate(date, USD_CODE);

        assertFalse(result.isPresent());
    }
}
