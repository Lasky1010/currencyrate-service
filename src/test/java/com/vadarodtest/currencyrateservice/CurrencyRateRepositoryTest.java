package com.vadarodtest.currencyrateservice;

import com.vadarodtest.currencyrateservice.data.entity.CurrencyRate;
import com.vadarodtest.currencyrateservice.repository.CurrencyRateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE,
        connection = EmbeddedDatabaseConnection.H2
)
public class CurrencyRateRepositoryTest {

    @Autowired
    private CurrencyRateRepository repository;

    @Test
    public void testSaveCurrencyRate() {
        var currencyRate = new CurrencyRate();
        currencyRate.setCurrencyCode("USD");

        repository.save(currencyRate);

        var all = repository.findAll();
        Assertions.assertEquals(1, all.size());
    }
}
