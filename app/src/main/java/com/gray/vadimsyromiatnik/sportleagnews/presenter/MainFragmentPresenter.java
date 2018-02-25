package com.gray.vadimsyromiatnik.sportleagnews.presenter;

import com.gray.vadimsyromiatnik.sportleagnews.view.MainFragmentView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by vadim on 25.01.18.
 */

public interface MainFragmentPresenter  extends MvpPresenter<MainFragmentView> {
    void showCurrentCityWeather(String lng, String lon);
    void getTodayTeamEventFromDatabase();
    void getTodaySportEventFromDatabase();
    void getTodayWeatherEventFromDatabase();
    void getTodayNewsFromDatabase();
    void getCommandAndLeague();
}
