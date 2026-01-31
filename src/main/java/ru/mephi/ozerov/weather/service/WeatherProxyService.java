package ru.mephi.ozerov.weather.service;

import java.math.BigDecimal;

public interface WeatherProxyService {

    String weatherFullInfo(BigDecimal latitude, BigDecimal longitude);

    Integer weatherFactTemp(BigDecimal latitude, BigDecimal longitude);

    BigDecimal weatherAvgTemp(BigDecimal latitude, BigDecimal longitude, Integer limit);

}
