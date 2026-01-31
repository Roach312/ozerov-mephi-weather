package ru.mephi.ozerov.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.ozerov.weather.service.WeatherProxyService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/weather-proxy/v1")
@RequiredArgsConstructor
public class WeatherProxyProxyController {

    private final WeatherProxyService weatherProxyService;

    @GetMapping(value = "/full-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getWeatherFullInfo(@RequestParam("lat") BigDecimal latitude, @RequestParam("lon") BigDecimal longitude) {
        return ResponseEntity.ok(weatherProxyService.weatherFullInfo(latitude, longitude));
    }

    @GetMapping(value = "/fact-temp")
    public ResponseEntity<Integer> getWeatherFactTemp(@RequestParam("lat") BigDecimal latitude, @RequestParam("lon") BigDecimal longitude) {
        return ResponseEntity.ok(weatherProxyService.weatherFactTemp(latitude, longitude));
    }

    @GetMapping(value = "/avg-temp")
    public ResponseEntity<BigDecimal> getWeatherAverageTemp(@RequestParam("lat") BigDecimal latitude, @RequestParam("lon") BigDecimal longitude, @RequestParam("limit") Integer limit) {
        return ResponseEntity.ok(weatherProxyService.weatherAvgTemp(latitude, longitude, limit));
    }

}
