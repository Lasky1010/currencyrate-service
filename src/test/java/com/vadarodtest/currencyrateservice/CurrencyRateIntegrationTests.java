package com.vadarodtest.currencyrateservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class CurrencyRateIntegrationTests {

    public static final String USD_CODE = "USD";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveRatesForDateAndGetByDateAndCurCode() throws Exception {
        mockMvc.perform(post("/api/currency")
                        .param("date", "2024-06-29"))
                .andExpect(status().isOk())
                .andExpect(content().string("Rates saved"));

        mockMvc.perform(get("/api/currency")
                        .param("date", "2024-06-29")
                        .param("cur_code", USD_CODE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.currencyCode").value(USD_CODE))
                .andExpect(jsonPath("$.date").value("2024-06-29"))
                .andExpect(jsonPath("$.rate").value(3.1624));

    }

}
