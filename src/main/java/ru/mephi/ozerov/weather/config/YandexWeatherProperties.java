package ru.mephi.ozerov.weather.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "yandex.weather")
public class YandexWeatherProperties {
    private String baseUrl;
    private String pathUrl;
    @ToString.Exclude
    private String weatherKey;
}
