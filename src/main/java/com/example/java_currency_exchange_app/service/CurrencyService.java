package com.example.java_currency_exchange_app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class CurrencyService {
    private static final String EXCHANGE_RATE_API = "https://api.exchangeratesapi.io/";
    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    private WebClient webClient = WebClient.create(EXCHANGE_RATE_API);

    public Map convertCurrencies(BigDecimal amount, String currency) {
        Map exchangeRate = webClient.get().uri(String.format("latest?base=%s", currency)).retrieve().bodyToMono(Map.class).block();
        Map rateMap = (Map) exchangeRate.get("rates");
        for(String currencyKey: (Set<String>) rateMap.keySet()) {
            BigDecimal rate = new BigDecimal((Double) rateMap.get(currencyKey));
            BigDecimal convertedCurrency = amount.multiply(rate).setScale(5, RoundingMode.HALF_UP);
            rateMap.put(currencyKey, convertedCurrency);
        }
        rateMap.put(currency, amount);
        return exchangeRate;
    }

    public Map getRateHistory(String currency) {
        Date today = new Date();
        LocalDateTime ldtPast = LocalDateTime.now().minusDays(29);
        Date past = Date.from(ldtPast.atZone(ZoneId.systemDefault()).toInstant());
        String endDate = SDF.format(today);
        String startDate = SDF.format(past);
        return webClient.get().uri(String.format("history?base=%s&start_at=%s&end_at=%s",
            currency, startDate, endDate))
            .retrieve().bodyToMono(Map.class).block();

    }
}
