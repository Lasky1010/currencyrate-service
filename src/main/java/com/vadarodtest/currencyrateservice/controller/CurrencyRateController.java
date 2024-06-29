package com.vadarodtest.currencyrateservice.controller;


import com.vadarodtest.currencyrateservice.data.entity.CurrencyRate;
import com.vadarodtest.currencyrateservice.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    @PostMapping
    public ResponseEntity<String> loadRatesForDate(@RequestParam("date")
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return currencyRateService.saveRatesForDate(date);
    }

    @GetMapping
    public ResponseEntity<CurrencyRate> getRateForDate(@RequestParam(value = "date", required = false)
                                                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                       @RequestParam("cur_code") String currencyCode) {
        Optional<CurrencyRate> rateForDate = currencyRateService.getRateForDate(date, currencyCode);
        return rateForDate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
