package com.example.java_currency_exchange_app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class CurrencyServiceTest {
    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private final CurrencyService currencyService = new CurrencyService();

    @Test
    public void test_convertCurrencies_success() {
        Map currencyConversion = currencyService.convertCurrencies(new BigDecimal(123), "USD");

        assertEquals("USD", currencyConversion.get("base"));
        assertEquals(new BigDecimal(123), ((Map) currencyConversion.get("rates")).get("USD"));
    }

    @Test(expected = Exception.class)
    public void test_convertCurrencies_throw_exception_if_invalid_amount() {
        currencyService.convertCurrencies(new BigDecimal("SSSSS"), "USD");
    }

    @Test(expected = Exception.class)
    public void test_convertCurrencies_throw_exception_if_invalid_currency() {
       currencyService.convertCurrencies(new BigDecimal(123), "ABCDEFG");
    }

    @Test
    public void test_getRateHistory_success() {
        Map rateHistory = currencyService.getRateHistory("USD");
        Date today = new Date();
        LocalDateTime ldtPast = LocalDateTime.now().minusDays(29);
        Date past = Date.from(ldtPast.atZone(ZoneId.systemDefault()).toInstant());
        String endDate = SDF.format(today);
        String startDate = SDF.format(past);
        assertEquals("USD", rateHistory.get("base"));
        assertEquals(startDate, rateHistory.get("start_at"));
        assertEquals(endDate, rateHistory.get("end_at"));
    }

    @Test(expected = Exception.class)
    public void test_getRateHistory_throw_exception_if_invalid_currency() {
        currencyService.getRateHistory("ABCDEFG");
    }
}
