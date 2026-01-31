package ru.mephi.ozerov.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private static final String WEATHER_KEY_HEADER_NAME = "X-Yandex-Weather-Key";

    @Bean("yandexWeatherRestClient")
    public RestClient yandexWeatherRestClient(YandexWeatherProperties yandexWeatherProperties) {
        return  RestClient.builder()
                .baseUrl(yandexWeatherProperties.getBaseUrl())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(WEATHER_KEY_HEADER_NAME, yandexWeatherProperties.getWeatherKey())
                .build();
    }
}
