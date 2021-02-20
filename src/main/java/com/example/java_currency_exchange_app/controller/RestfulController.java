package com.example.java_currency_exchange_app.controller;

import com.example.java_currency_exchange_app.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class RestfulController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("currencyConversion/{amount}/{currency}")
    private Map convertCurrencies(
        @PathVariable BigDecimal amount,
        @PathVariable String currency) throws Exception {
        return currencyService.convertCurrencies(amount, currency);
    }

    @GetMapping("rateHistory/{currency}")
    private Map gatRateHistory(@PathVariable String currency) throws Exception {
        return currencyService.getRateHistory(currency);
    }
}
