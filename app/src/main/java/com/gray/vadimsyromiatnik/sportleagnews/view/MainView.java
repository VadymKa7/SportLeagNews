package com.gray.vadimsyromiatnik.sportleagnews.view;

import android.support.v7.widget.RecyclerView;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by vadim on 18.12.17.
 */

public interface MainView extends MvpView{

    void showEventFormServer(String message);

    void showTodayNewList(RecyclerView.Adapter arrayAdapter);

    void showCurrentCityWeather(String weather_city, String weather_description, String weather_temperature, String weather_updatedOn, String weather_iconText, String sun_rise);

    void showCommandAndLeague(String league, String command);
}
