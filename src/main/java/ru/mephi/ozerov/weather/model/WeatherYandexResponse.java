package ru.mephi.ozerov.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WeatherYandexResponse(@JsonProperty("fact") WeatherFact weatherFact, @JsonProperty("forecasts") List<Forecast> forecast) {
    public record WeatherFact(@JsonProperty("temp") int temp) {
    }
    public record Forecast(@JsonProperty("parts") ForecastParts parts) {
        public record ForecastParts(@JsonProperty("day") DayPart day) {
            public record DayPart(@JsonProperty("temp_avg") int tempAvg) {
            }
        }
    }
}
