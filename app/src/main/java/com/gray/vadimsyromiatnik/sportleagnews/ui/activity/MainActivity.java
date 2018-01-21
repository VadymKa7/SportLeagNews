package com.gray.vadimsyromiatnik.sportleagnews.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.gray.vadimsyromiatnik.sportleagnews.R;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.MainPresenter;
import com.gray.vadimsyromiatnik.sportleagnews.presenter.MainPresenterImpl;
import com.gray.vadimsyromiatnik.sportleagnews.view.MainView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;


public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {
    private static final String TAG = "MainActivity";
    @BindView(R.id.cityField)TextView cityField;
    @BindView(R.id.updatedField)TextView updatedField;
    @BindView(R.id.detailsField)TextView detailsField;
    @BindView(R.id.currentTemperatureField)TextView currentTemperatureField;
    @BindView(R.id.tvTodayEventMain)TextView tvTodayEventMain;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    TextView weatherIcon;
    Typeface weatherFont;
    @Inject
    MainPresenterImpl mainPresenter;

    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
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

        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // используем linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        getPresenter().showCurrentCityWeather();
        getPresenter().getTodayEventFromDatabase();
        getPresenter().getTodayNewsFromDatabase();

    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return mainPresenter;
    }



    @Override
    public void showEventFormServer(String event) {
        tvTodayEventMain.setText(event);
    }

    @Override
    public void showTodayNewList( RecyclerView.Adapter arrayAdapter) {
        mRecyclerView.setAdapter(arrayAdapter);
    }

    @Override
    public void showCurrentCityWeather(String weather_city, String weather_description, String weather_temperature, String weather_updatedOn, String weather_iconText, String sun_rise) {
        cityField.setText(weather_city);
        updatedField.setText(weather_updatedOn);
        detailsField.setText(weather_description);
        currentTemperatureField.setText(weather_temperature);
        weatherIcon.setText(Html.fromHtml(weather_iconText));
    }



    @Override
    public void showCommandAndLeague(String league, String command) {
        Log.d(TAG, "showCommandAndLeague: " + league);
        Log.d(TAG, "showCommandAndLeague: " + command);
    }
}
