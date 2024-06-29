package com.vadarodtest.currencyrateservice;

import com.vadarodtest.currencyrateservice.data.entity.CurrencyRate;
import com.vadarodtest.currencyrateservice.repository.CurrencyRateRepository;
import com.vadarodtest.currencyrateservice.service.CurrencyRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CurrencyRateControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyRateService currencyRateService;
    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    @Test
    public void testLoadRatesForDate() throws Exception {
        LocalDate date = LocalDate.of(2024, 6, 29);

        when(currencyRateService.saveRatesForDate(date))
                .thenReturn(ResponseEntity.ok("Rates saved"));

        mockMvc.perform(post("/api/currency")
                        .param("date", "2024-06-29"))
                .andExpect(status().isOk())
                .andExpect(content().string("Rates saved"));
    }

    @Test
    public void testGetRateForDate() throws Exception {
        LocalDate date = LocalDate.of(2024, 6, 29);
        String currencyCode = "USD";

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setDate(date);
        currencyRate.setCurrencyCode(currencyCode);
        currencyRate.setRate(3.0);

        currencyRateRepository.save(currencyRate);
        when(currencyRateService.getRateForDate(date, currencyCode))
                .thenReturn(Optional.of(currencyRate));

        MvcResult result = mockMvc.perform(get("/api/currency")
                        .param("date", "2023-06-29")
                        .param("cur_code", "USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyCode").value("USD"))
                .andExpect(jsonPath("$.rate").value(3.0))
                .andReturn();
        System.out.println("Response: " + result.getResponse().getContentAsString());
    }
}
