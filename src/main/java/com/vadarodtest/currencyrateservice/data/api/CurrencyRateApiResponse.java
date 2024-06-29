package com.vadarodtest.currencyrateservice.data.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CurrencyRateApiResponse {

    @JsonProperty("Cur_ID")
    private int id;

    @JsonProperty("Date")
    private LocalDate date;

    @JsonProperty("Cur_Abbreviation")
    private String currencyCode;

    @JsonProperty("Cur_Scale")
    private int scale;

    @JsonProperty("Cur_Name")
    private String name;

    @JsonProperty("Cur_OfficialRate")
    private Double rate;

}
