package com.gray.vadimsyromiatnik.sportleagnews.ui.activity.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.MainFragmentPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.view.MainFragmentView;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;


public class MainFragment extends MvpFragment<MainFragmentView, MainFragmentPresenter> implements MainFragmentView {

    private static final String TAG = "MainActivity";
    @BindView(R.id.detailsField)TextView detailsField;
    @BindView(R.id.currentTemperatureField)TextView currentTemperatureField;
    @BindView(R.id.weather_icon)TextView weatherIcon;
    @BindView(R.id.tvTodayEventMain)TextView tvTodayEventMain;
    @BindView(R.id.recyclerViewNews)RecyclerView recyclerViewNews;
    private RecyclerView.LayoutManager mLayoutManager;
    Typeface weatherFont;
    @Inject MainFragmentPresenter mainFragmentPresenter;

    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return view;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    Log.d(TAG, "onSuccess: " + location.getLatitude());
                    Log.d(TAG, "onSuccess: " + location.getLongitude());
                    getPresenter().showCurrentCityWeather(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()));
                    // Logic to handle location object
                }
            }
        });

        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        weatherIcon.setTypeface(weatherFont);
//        recyclerViewNews.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerViewNews.setLayoutManager(mLayoutManager);

        getPresenter().getTodayNewsFromDatabase();
        getPresenter().getTodayEventFromDatabase();

        return view;
    }




    @Override
    public void showEventFormServer(String event) {
        tvTodayEventMain.setText(event);
    }

    @Override
    public void showTodayNewList( RecyclerView.Adapter arrayAdapter) {
        recyclerViewNews.setAdapter(arrayAdapter);
    }

    @Override
    public void showCurrentCityWeather(String weather_city, String weather_description, String weather_temperature, String weather_updatedOn, String weather_iconText, String sun_rise) {
        detailsField.setText(weather_description);
        currentTemperatureField.setText(weather_temperature);
        weatherIcon.setText(Html.fromHtml(weather_iconText));
    }


    @Override
    public void showCommandAndLeague(String league, String command) {
        Log.d(TAG, "showCommandAndLeague: " + league);
        Log.d(TAG, "showCommandAndLeague: " + command);
    }

    @Override
    public MainFragmentPresenter createPresenter() {
        return mainFragmentPresenter;
    }
}
