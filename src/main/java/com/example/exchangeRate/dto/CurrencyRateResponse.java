package com.example.exchangeRate.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyRateResponse
{
    private Meta meta;
    private List<TimeSeriesData> values;

    @Data
    public static class Meta {
        private String symbol;
        private String interval;
    }

    @Data
    public static class TimeSeriesData {
        private String datetime;
        private String close;
    }
}
