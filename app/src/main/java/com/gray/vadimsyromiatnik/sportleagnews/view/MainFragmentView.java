package com.gray.vadimsyromiatnik.sportleagnews.view;

import android.support.v7.widget.RecyclerView;

import com.gray.vadimsyromiatnik.sportleagnews.models.BestEvent;
import com.gray.vadimsyromiatnik.sportleagnews.models.CommandNews;
import com.gray.vadimsyromiatnik.sportleagnews.models.Plan;
import com.gray.vadimsyromiatnik.sportleagnews.models.Weather;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by vadim on 25.01.18.
 */

public interface MainFragmentView extends MvpView {

    void showEventFormServer(CommandNews message);

    void showWeatherEventFormServer(Weather weather);

    void showPlanEventFormServer(Plan plan);

    void showBestEventFormServer(BestEvent plan);

    void showTodayNewList(RecyclerView.Adapter arrayAdapter);

    void showCurrentCityWeather(String weather_city, String weather_description, String weather_temperature, String weather_updatedOn, String weather_iconText, String sun_rise);

    void showCommandAndLeague(String league, String command);

    String splitWeatherTemperatureView(String temperature);
}
