package com.coolweather.tqzzd.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wonder on 17-3-29.
 */

public class Weather {

    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

}
