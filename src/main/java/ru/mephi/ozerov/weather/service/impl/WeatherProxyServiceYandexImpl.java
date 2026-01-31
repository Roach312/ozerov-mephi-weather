package ru.mephi.ozerov.weather.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.mephi.ozerov.weather.config.YandexWeatherProperties;
import ru.mephi.ozerov.weather.model.WeatherYandexResponse;
import ru.mephi.ozerov.weather.service.WeatherProxyService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherProxyServiceYandexImpl implements WeatherProxyService {
    private static final String LATITUDE_PARAM_NAME = "lat";
    private static final String LONGITUDE_PARAM_NAME = "lon";

    @Qualifier("yandexWeatherRestClient")
    private final RestClient yandexWeatherRestClient;
    private final YandexWeatherProperties yandexWeatherProperties;

    @Override
    public String weatherFullInfo(BigDecimal latitude, BigDecimal longitude) {
        return yandexWeatherRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(yandexWeatherProperties.getPathUrl())
                        .queryParam(LATITUDE_PARAM_NAME, latitude)
                        .queryParam(LONGITUDE_PARAM_NAME, longitude)
                        .build())
                .retrieve()
                .body(String.class);
    }

    @Override
    public Integer weatherFactTemp(BigDecimal latitude, BigDecimal longitude) {
        return getWeatherYandexResponse(latitude, longitude).weatherFact().temp();
    }

    @Override
    public BigDecimal weatherAvgTemp(BigDecimal latitude, BigDecimal longitude, Integer limit) {
        return BigDecimal.valueOf(getWeatherYandexResponse(latitude, longitude).forecast().stream()
                .limit(limit)
                .map(forecast -> forecast.parts().day().tempAvg())
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0)).setScale(2, RoundingMode.HALF_UP);
    }

    private WeatherYandexResponse getWeatherYandexResponse(BigDecimal latitude, BigDecimal longitude) {
        return yandexWeatherRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(yandexWeatherProperties.getPathUrl())
                        .queryParam(LATITUDE_PARAM_NAME, latitude)
                        .queryParam(LONGITUDE_PARAM_NAME, longitude)
                        .build())
                .retrieve()
                .body(WeatherYandexResponse.class);
    }
}
