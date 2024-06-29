package com.vadarodtest.currencyrateservice.data.mapper;

import com.vadarodtest.currencyrateservice.data.api.CurrencyRateApiResponse;
import com.vadarodtest.currencyrateservice.data.entity.CurrencyRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurrencyRateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currencyCode", source = "currencyCode")
    @Mapping(target = "rate", source = "rate")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "name", source = "name")
    List<CurrencyRate> mapTo(List<CurrencyRateApiResponse> apiResponse);

}
